package c.domatron.taggame.Fragments

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import c.domatron.taggame.R
import c.domatron.taggame.Utilities.database
import kotlinx.android.synthetic.main.activity_join_group.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.toast
import android.net.wifi.WifiInfo
import android.content.Context.WIFI_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.net.wifi.WifiManager
import android.content.Context


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
        //unique way to identify each user
        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wInfo = wifiManager.connectionInfo
        val macAddress = wInfo.macAddress

        //TODO: add if/when to make sure there is a username and group code
        if(joinGroupCode != null && joinUname != null) {
            database.use {
                insert("Group",
                    "user" to joinUname,
                    "tid" to null, "status" to 0,
                    "groupId" to joinGroupCode,
                    "macAddrs" to macAddress)
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }else{
            toast("Invalid format. Please try again")
        }

    }

}
