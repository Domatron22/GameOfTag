package c.domatron.taggame.Fragments

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.os.Build
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import c.domatron.taggame.DAO.Player
import c.domatron.taggame.R
import c.domatron.taggame.Utilities.NFCUtil.onNfcDetected
import c.domatron.taggame.Utilities.database
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast
import java.net.URI

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
    var enabled : Boolean = false
    //val tagDatabase = database.writableDatabase
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->


        when (item.itemId) {
            R.id.navigation_home -> {
                openFragment(HomeFragment.newInstance())
                return@OnNavigationItemSelectedListener true
                //message.setText(R.string.title_home)
                //Still have to add fragments, this is just to start writing the body before the fragments are made
                //mNfcAdapter = NfcAdapter.getDefaultAdapter(this)
                //val confirm = NFCUtil.retrieveNFCMessage(this.intent)
            }

//            R.id.navigation_tag -> {
//                openFragment(TagFragment.newInstance())
//                return@OnNavigationItemSelectedListener true
//            }

            R.id.navigation_leaderboard -> {
                openFragment(LeaderFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_tagadd -> {
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

//        startDownloading()

//        database.use{
//            select("Group", "User").whereArgs("macAddrs = $macAddress")
//        }
    }

//    private val STORAGE_PERMISSION_CODE: Int = 1000

//    fun checkDatabase()
//    {
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
//        {
//            if(ContextCompat.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
//            {
//                //Permission is denied, request permission
//                ActivityCompat.requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), STORAGE_PERMISSION_CODE)
//            }else{
//                //Permission already granted
//                startDownloading()
//            }
//        }else{
//            //SYStem os is less than marshmallow
//            startDownloading()
//        }
//    }

//    fun startDownloading()
//    {
//        val url = "http://web.cs.sunyit.edu/~trianod/Players.db"
//
//        val request = DownloadManager.Request(Uri.parse(url))
//        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
//        request.setTitle("Download")
//        request.setDescription("The file is downloading...")
//
//        request.allowScanningByMediaScanner()
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
//        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "${System.currentTimeMillis()}")
//
//        val manager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
//        manager.enqueue(request)
//    }

//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        when(requestCode)
//        {
//            STORAGE_PERMISSION_CODE -> {
//                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    startDownloading()
//                } else {
//                    toast("Permission Denied")
//                }
//            }
//        }
//    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    val TAG = MainActivity::class.java.simpleName

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if(enabled) {
            readNFC(intent)
        }
    }

    fun readNFC(intent : Intent?)
    {
        val tag = intent?.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)

        Log.d(TAG, "onNewIntent: " + intent?.getAction()!!)

        if (tag != null) {
            Toast.makeText(this, "NFC Tag Detected", Toast.LENGTH_SHORT).show()
            val ndef = Ndef.get(tag)

            println("\t" + onNfcDetected(ndef))


        }

        enabled = false
    }

}

