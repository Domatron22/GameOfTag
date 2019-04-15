package c.domatron.taggame.Fragments

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.nfc.NfcAdapter
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import c.domatron.taggame.R
import c.domatron.taggame.Utilities.NFCUtil
import c.domatron.taggame.Utilities.SQLManager
import c.domatron.taggame.Utilities.database

import kotlinx.android.synthetic.main.activity_add_tag.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

/* Author: Dominic Triano
 * Date: 4/3/2019
 * Language: Kotlin
 * Project: TagGame
 * Description:
 * This activity adds tags to the players entry
 *
 */
class AddTagActivity : AppCompatActivity() {

    private var mNfcAdapter: NfcAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_tag)
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this)

        initView()



    }

    fun initView()
    {
        registerButton.setOnClickListener { view -> addTag() }
    }

    override fun onResume() {
        super.onResume()
        mNfcAdapter?.let {
            NFCUtil.enableNFCInForeground(it, this, javaClass)
        }
    }

    override fun onPause() {
        super.onPause()
        mNfcAdapter?.let {
            NFCUtil.disableNFCInForeground(it, this)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val messageWrittenSuccessfully = NFCUtil.createMessage(tagId.text.toString(), intent)
        toast(messageWrittenSuccessfully.ifElse("Successful Written to Tag", "Something When wrong Try Again"))
    }

    fun addTag()
    {
        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wInfo = wifiManager.connectionInfo
        val macAddress = wInfo.macAddress

        //Initialize database
        val dbHandler = SQLManager(this)

        //inputted tagid gets pushed and written to tag
        dbHandler.setTag(tagId.text.toString(), macAddress)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}

fun <T> Boolean.ifElse(primaryResult: T, secondaryResult: T) = if (this) primaryResult else secondaryResult