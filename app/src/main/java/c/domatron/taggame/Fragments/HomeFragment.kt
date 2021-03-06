package c.domatron.taggame.Fragments


import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import c.domatron.taggame.R
import c.domatron.taggame.Utilities.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast
import android.nfc.FormatException
import android.nfc.NdefMessage
import android.nfc.tech.Ndef
import android.content.ContentValues.TAG
import java.io.IOException


/* Author: Dominic Triano
 * Date: 2/5/2019
 * Language: Kotlin
 * Project: TagGame
 * Description:
 *
 *
 */

class HomeFragment : Fragment() {

//    private lateinit var hTitle: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_home, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
//        val uName = activity?.database?.use {
//            select("Group", "user").whereArgs("macAddrs = $macAddress")
//        }

        //title in the home fragment
        val uName : String = chkUser(getMacAddr())!!
        println("\t 101 $uName")

        homeTitle.text = "Welcome back $uName"


        //Finds the players status and tells the player accordingly
        val status : String = chkStatus(uName)!!
        println("\t 101 $status")

        when(status)
        {
            "1" -> homeStatus.text = "You are currently it!\nThe clock is ticking, find your opponent."
            "0" -> homeStatus.text = "You are currently not it.\nHowever, be on the lookout for bloodthursty teammates."
        }
    }

    fun setupButtons()
    {
        tagEnable.setOnClickListener {
                view -> tagEnable()
        }

    }

    fun tagEnable()
    {
        val status = chkStatus(chkUser(getMacAddr())!!)
        //TODO -- Finish the NFC read object and have it read the tag then set your status to 0 and theirs to 1

        if(status == "1") {
            val intent = Intent(activity, MainActivity::class.java)
            MainActivity().enabled = true
            Toast.makeText(activity, "Scanning for Tag, get in range!", Toast.LENGTH_LONG).show()
            MainActivity().readNFC(intent)
        }else{
            Toast.makeText(activity, "You are currently not it", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

//    fun onNfcDetected(ndef: Ndef) {
//
//        readFromNFC(ndef)
//    }
//
//    private fun readFromNFC(ndef: Ndef) {
//
//        try {
//            ndef.connect()
//            val ndefMessage = ndef.ndefMessage
//            val message = String(ndefMessage.records[0].payload)
//            Log.d(TAG, "readFromNFC: $message")
//            mTvMessage.setText(message)
//            ndef.close()
//
//        } catch (e: IOException) {
//            e.printStackTrace()
//
//        } catch (e: FormatException) {
//            e.printStackTrace()
//        }
//
//    }
}
