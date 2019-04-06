package c.domatron.taggame.Fragments

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import c.domatron.taggame.R
import c.domatron.taggame.Utilities.database
import kotlinx.android.synthetic.main.activity_create_group.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.toast

/* Author: Dominic Triano
 * Date: 4/3/2019
 * Language: Kotlin
 * Project: TagGame
 * Description:
 *
 *
 */

class CreateGroupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_group)

        initView()
    }

    fun initView(){
        createButton.setOnClickListener { view -> verify() }
    }

    fun verify(){
        //TODO: check the website every 5 seconds to see if anyone has joined to confirm

        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wInfo = wifiManager.connectionInfo
        val macAddress = wInfo.macAddress

        if(createGroupCode != null && createUname != null) {
            database.use {
                insert("Group",
                    "user" to createUname,
                    "tid" to 0,
                    "status" to 0,
                    "groupId" to createGroupCode,
                    "macAddrs" to macAddress)
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }else{
            toast("Invalid format. Please try again")
        }

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}