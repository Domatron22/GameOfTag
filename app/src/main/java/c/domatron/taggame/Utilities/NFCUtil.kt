package c.domatron.taggame.Utilities

import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.*
import android.nfc.tech.Ndef
import android.nfc.tech.NdefFormatable
import android.util.Log
import c.domatron.taggame.Fragments.HomeFragment
import java.io.IOException
/* Author: Dominic Triano
 * Date: 4/3/2019
 * Language: Kotlin
 * Project: TagGame
 * Description:
 *  Went through a tutorial by perterjohnwelcome.com to learn NFC
 *
 *  This activity creates an object that allows the user to read and write to NFC tags
 *
 */
object NFCUtil
{
    /*TODO -- Finish and test*/

    fun createMessage(payload: String, intent: Intent?): Boolean {

        val pathPrefix = "c.domatron.taggame"
        val nfcRecord = NdefRecord(NdefRecord.TNF_EXTERNAL_TYPE, pathPrefix.toByteArray(), ByteArray(0), payload.toByteArray())
        val nfcMessage = NdefMessage(arrayOf(nfcRecord))
        intent?.let {
            val tag = it.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
            return writeMessageToTag(nfcMessage, tag)
        }
        return false
    }

    fun retrieveNFCMessage(intent: Intent?): String {
        intent?.let {
            if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action)
            {
                val nDefMessages = getNDefMessages(intent)
                nDefMessages[0].records?.let{
                    it.forEach{
                        it?.payload.let {
                            it?.let {
                                return String(it)
                            }
                        }
                    }
                }

            } else {
                return "Touch Tag to Scan"
            }
        }
        return "Touch Tag to Scan"
    }


    private fun getNDefMessages(intent: Intent): Array<NdefMessage>
    {

        val rawMessage = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
        rawMessage?.let {
            return rawMessage.map {
                it as NdefMessage
            }.toTypedArray()
        }
        // Unknown tag type
        val empty = byteArrayOf()
        val record = NdefRecord(NdefRecord.TNF_UNKNOWN, empty, empty, empty)
        val msg = NdefMessage(arrayOf(record))
        return arrayOf(msg)
    }

    //self explanitory
    fun disableNFCInForeground(nfcAdapter: NfcAdapter, activity: Activity) {
        nfcAdapter.disableForegroundDispatch(activity)
    }

    //self explanitory again
    fun <T> enableNFCInForeground(nfcAdapter: NfcAdapter, activity: Activity, classType: Class<T>) {
        val pendingIntent = PendingIntent.getActivity(
            activity, 0,
            Intent(activity, classType).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0
        )
        val nfcIntentFilter = IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED)
        val filters = arrayOf(nfcIntentFilter)

        val TechLists = arrayOf(arrayOf(Ndef::class.java.name), arrayOf(NdefFormatable::class.java.name))

        nfcAdapter.enableForegroundDispatch(activity, pendingIntent, filters, TechLists)
    }

    //takes the text from the fragment and writes it to a tag
    private fun writeMessageToTag(nfcMessage: NdefMessage, tag: Tag?): Boolean {

        try {
            val nDefTag = Ndef.get(tag)

            nDefTag?.let {
                it.connect()
                if (it.maxSize < nfcMessage.toByteArray().size) {
                    //Message to large to write to NFC tag
                    return false
                }
                if (it.isWritable) {
                    it.writeNdefMessage(nfcMessage)
                    it.close()
                    //Message is written to tag
                    return true
                } else {
                    //NFC tag is read-only
                    return false
                }
            }

            val nDefFormatableTag = NdefFormatable.get(tag)

            nDefFormatableTag?.let {
                try {
                    it.connect()
                    it.format(nfcMessage)
                    it.close()
                    //The data is written to the tag
                    return true
                } catch (e: IOException) {
                    //Failed to format tag
                    return false
                }
            }
            //NDEF is not supported
            return false

        } catch (e: Exception) {
            //Write operation has failed
        }
        return false
    }

    val TAG = HomeFragment::class.java!!.getSimpleName()

    fun onNfcDetected(ndef: Ndef) {

        readFromNFC(ndef)
    }

    private fun readFromNFC(ndef: Ndef) {

        try {
            ndef.connect()
            val ndefMessage = ndef.ndefMessage
            val message = String(ndefMessage.records[0].payload)
            Log.d(TAG, "readFromNFC: $message")
            ndef.close()

        } catch (e: IOException) {
            e.printStackTrace()

        } catch (e: FormatException) {
            e.printStackTrace()
        }

    }
}