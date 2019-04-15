package c.domatron.taggame.Fragments

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import c.domatron.taggame.R
import kotlinx.android.synthetic.main.activity_join_group.*
import org.jetbrains.anko.toast
import android.net.wifi.WifiInfo
import android.content.Context.WIFI_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.net.wifi.WifiManager
import android.content.Context
import c.domatron.taggame.DAO.Player
import c.domatron.taggame.DAO.TagDatabase
import c.domatron.taggame.Utilities.Players
import c.domatron.taggame.Utilities.SQLManager
import c.domatron.taggame.Utilities.database
import org.jetbrains.anko.db.insert

/* Author: Dominic Triano
 * Date: 4/3/2019
 * Language: Kotlin
 * Project: TagGame
 * Description:
 * This activity allows you to join an existing group by checking what exists on the online database
 *
 */

class JoinGroupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_group)

        initView()
    }

    fun initView(){
        joinButton.setOnClickListener { view -> verify() }

    }

    fun verify(){
        //TODO -- If the groupcode exists in the library, then you can execute this code.

        //unique way to identify each user
        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wInfo = wifiManager.connectionInfo
        val macAddress = wInfo.macAddress


        //if the fields are satisfied
        if(joinGroupCode.text!!.isNotEmpty() && joinUname.text!!.isNotEmpty()) {
            //start the database
            val dbHandler = SQLManager(this)

            val player = Players()
            var success = false

            player.user = joinUname.text.toString() //username
            player.tid = "" //placeholder for tag
            player.groupId = joinGroupCode.text.toString() //group the player is in
            player.status = 0 //if they are "it" or not (0 is not it)
            player.tcount = 0 //How many times they have been tagged
            player.macAddrs = macAddress //unique identifier for the user

            success = dbHandler!!.addUser(player)

            if(success)
            {
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
                dbHandler.fetchDatabase()
            }else{
                toast("Unknown Error. Please try again")
            }


        }else{
            toast("Invalid format. Please try again")
        }

    }

}
