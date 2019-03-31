package c.domatron.taggame

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.support.v4.app.Fragment
import android.arch.persistence.room.Query
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
        setContentView(R.layout.content_log_in)
        var approval = false

        SignInButton.setOnClickListener {

            var verification = verify(Uname.toString(), Pass.toString())
            when(verification)
            {
                0 -> approval = true
                1 -> toast("Incorrect Password. Please try again.")
                2 -> toast("Unknown User. Please try again.")
                5 -> error("Unknown Error Occurred, Please try again")
            }
        }

        if(approval) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        }
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun verify(user: String, pass: String): Int
    {
        var verification = 0
        var player = Player( user,"",pass, 0 )

        /*TODO -- Send queries to the room database to verify the user*/


        when(verification)
        {
            0 -> return 0
            1 -> return 1
            2 -> return 2
        }

        return 5
    }

}
