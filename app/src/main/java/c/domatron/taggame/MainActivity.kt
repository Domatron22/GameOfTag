package c.domatron.taggame

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.nfc.NfcAdapter

class MainActivity : AppCompatActivity() {
    private var mNfcAdapter : NfcAdapter? = null
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                //Still have to add fragments, this is just to start writing the body before the fragments are made
                mNfcAdapter = NfcAdapter.getDefaultAdapter(this)
                val confirm = NFCUtil.retrieveNFCMessage(this.intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_tag -> {
                message.setText(R.string.title_tag)

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_leaderboard -> {
                message.setText(R.string.title_leaderboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_tagadd -> {
                message.setText(R.string.title_tagadd)

                return@OnNavigationItemSelectedListener true

            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
