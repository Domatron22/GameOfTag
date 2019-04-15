package c.domatron.taggame.Fragments

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.nfc.NfcAdapter
import android.support.v4.app.Fragment
import c.domatron.taggame.DAO.Player
import c.domatron.taggame.R
import c.domatron.taggame.Utilities.database
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

/* Author: Dominic Triano
 * Date: 2/5/2019
 * Language: Kotlin
 * Project: TagGame
 * Description:
 * This activity keeps the main fragments accessible
 *
 */

class MainActivity : AppCompatActivity() {
    private var mNfcAdapter : NfcAdapter? = null
    //val tagDatabase = database.writableDatabase
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->


        when (item.itemId) {
            R.id.navigation_home -> {
                println("TESTING HOME")
                openFragment(HomeFragment.newInstance())
                return@OnNavigationItemSelectedListener true
                //message.setText(R.string.title_home)
                //Still have to add fragments, this is just to start writing the body before the fragments are made
                //mNfcAdapter = NfcAdapter.getDefaultAdapter(this)
                //val confirm = NFCUtil.retrieveNFCMessage(this.intent)
            }

//            R.id.navigation_tag -> {
//                println("TESTING TAG")
//                openFragment(TagFragment.newInstance())
//                return@OnNavigationItemSelectedListener true
//            }

            R.id.navigation_leaderboard -> {
                println("TESTING LEADER")
                openFragment(LeaderFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_tagadd -> {
                println("TESTING TA")
                //TODO -- If there is no existing tag in the room, then go to the activity, otherwise Output it
                openFragment(AddTFragment.newInstance())
                return@OnNavigationItemSelectedListener true

            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        openFragment(HomeFragment.newInstance())

        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wInfo = wifiManager.connectionInfo
        val macAddress = wInfo.macAddress

//        database.use{
//            select("Group", "User").whereArgs("macAddrs = $macAddress")
//        }

        println("Entering Main")
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
