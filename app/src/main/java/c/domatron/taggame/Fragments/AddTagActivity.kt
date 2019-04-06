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
import c.domatron.taggame.Utilities.database

import kotlinx.android.synthetic.main.activity_add_tag.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

/**
 * Fragment to add tags to your player name so it can be synced with the website
 *
 */
class AddTagActivity : AppCompatActivity() {

    private var mNfcAdapter: NfcAdapter? = null
    val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    val wInfo = wifiManager.connectionInfo
    val macAddress = wInfo.macAddress

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
        database.use{
            select("Group")
                .whereArgs("macAddrs = $macAddress",
            "tid" to tagId)
        }
        toast(NFCUtil.retrieveNFCMessage(this.intent))
    }
}

fun <T> Boolean.ifElse(primaryResult: T, secondaryResult: T) = if (this) primaryResult else secondaryResult