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
import c.domatron.taggame.Utilities.database
import org.jetbrains.anko.db.insert


class JoinGroupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_group)

//        val db = Room.databaseBuilder(
//            applicationContext,
//            TagDatabase::class.java, "tag-db"
//        ).build()

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

        if(joinGroupCode.text!!.isNotEmpty() && joinUname.text!!.isNotEmpty()) {


//            val user = Player(joinUname.text.toString(), "", 0, 0, macAddress, joinGroupCode.text.toString())
//            db.insert(user)

            database.use {
                insert("Groups",
                    "user" to joinUname.text.toString(),
                    "tid" to null, "status" to 0,
                    "groupId" to joinGroupCode.text.toString(),
                    "macAddrs" to macAddress)
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }else{
            toast("Invalid format. Please try again")
        }

    }

}
