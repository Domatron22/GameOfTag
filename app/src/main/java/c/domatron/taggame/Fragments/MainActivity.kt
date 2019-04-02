package c.domatron.taggame.Fragments

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.nfc.NfcAdapter
import android.support.v4.app.Fragment
import c.domatron.taggame.DAO.Player
import c.domatron.taggame.R
import kotlinx.android.synthetic.main.activity_log_in.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    private var mNfcAdapter : NfcAdapter? = null
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_home -> {
                val homeFragment = HomeFragment.newInstance()
                openFragment(homeFragment)
                return@OnNavigationItemSelectedListener true
                //message.setText(R.string.title_home)
                //Still have to add fragments, this is just to start writing the body before the fragments are made
                //mNfcAdapter = NfcAdapter.getDefaultAdapter(this)
                //val confirm = NFCUtil.retrieveNFCMessage(this.intent)
            }

            R.id.navigation_tag -> {
                val tagFragment = TagFragment.newInstance()
                openFragment(tagFragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_leaderboard -> {
                val leaderFragment = LeaderFragment.newInstance()
                openFragment(leaderFragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_tagadd -> {
                val addFragment = AddTFragment.newInstance()
                openFragment(addFragment)
                return@OnNavigationItemSelectedListener true

            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}