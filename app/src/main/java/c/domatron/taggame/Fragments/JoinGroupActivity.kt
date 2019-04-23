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
import java.net.NetworkInterface
import java.util.*

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
            player.macAddrs = getMacAddr() //unique identifier for the user

            success = dbHandler!!.addUser(player)

            if(success)
            {
                var confirm = dbHandler.fetchDatabase()
                when(confirm)
                {
                    1 -> {  val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)}
                    -1 -> toast("Could not connect to database")
                }
            }else{
                toast("Unknown Error. Please try again")
            }


        }else{
            toast("Invalid format. Please try again")
        }

    }

    fun getMacAddr(): String {
        try {
            val all = Collections.list(NetworkInterface.getNetworkInterfaces())
            for (nif in all) {
                if (!nif.getName().equals("wlan0")) continue

                val macBytes = nif.getHardwareAddress() ?: return ""

                val res1 = StringBuilder()
                for (b in macBytes) {
                    //Taking a signed byte it will do a sign ext, the 255 does a bitwise and in order
                    //to turn off the Higher order bits. If this is not done, the Higher order bits will all be 1.
                    //The 255 has all zeros before its last 8 bits, so it flips all of the ones in the higher order to a 0
                    //Yes this seems unneccisary, but it cannot
                    var x = Integer.toHexString(b.toInt() and 255)
                    res1.append(x + ":")
                }

                if (res1.length > 0) {
                    res1.deleteCharAt(res1.length - 1)
                }
                return res1.toString()
            }
        } catch (ex: Exception) {
        }

        return "02:00:00:00:00:00"
    }

}
