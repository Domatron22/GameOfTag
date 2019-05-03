package c.domatron.taggame.Fragments


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import c.domatron.taggame.R
import kotlinx.android.synthetic.main.activity_join_group.*
import org.jetbrains.anko.toast
import c.domatron.taggame.Utilities.*
import kotlinx.android.synthetic.main.activity_create_group.*

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
        //if the fields are satisfied
        if(joinGroupCode.text!!.isNotEmpty() && joinUname.text!!.isNotEmpty()) {

            if(joinGroup(joinUname.text.toString(), getMacAddr(), joinGroupCode.text.toString()))
            {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                toast("Group Not Found, Try Again Later")
            }

//            start the database
//            UPDATE: No longer using local database, all commented code is for later implementation
//            val dbHandler = SQLManager(this)
//
//            val player = Players()
//            var success = false
//
//            player.user = joinUname.text.toString() //username
//            player.tid = "" //placeholder for tag
//            player.groupId = joinGroupCode.text.toString() //group the player is in
//            player.status = 0 //if they are "it" or not (0 is not it)
//            player.tcount = 0 //How many times they have been tagged
//            player.macAddrs = getMacAddr() //unique identifier for the user
//
//            success = dbHandler!!.addUser(player)
        }else{
            toast("Invalid format. Please try again")
        }

    }
}
