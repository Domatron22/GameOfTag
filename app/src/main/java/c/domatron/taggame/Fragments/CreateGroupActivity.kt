package c.domatron.taggame.Fragments

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import c.domatron.taggame.R
import c.domatron.taggame.Utilities.*
import kotlinx.android.synthetic.main.activity_create_group.*
import org.jetbrains.anko.toast

/* Author: Dominic Triano
 * Date: 4/3/2019
 * Language: Kotlin
 * Project: TagGame
 * Description:
 * This activity allows a player to create a group in the online database for others to join
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
        val gCode = createGroupCode.text.toString()
        val uName = createUname.text.toString()

        if(createGroupCode.text!!.isNotBlank() && createUname.text!!.isNotBlank()) {



//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)

            if(regGroup(createUname.text.toString(), getMacAddr(), createGroupCode.text.toString())!!.isEmpty())
            {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                toast("Group ID Taken, Please Try Again")
            }

//            start the database
//            UPDATE: No longer using local database, all commented code is for later implementation
//            val dbHandler = SQLManager(this)

//            val player = Players()
//            var success = false

//            player.user = createUname.text.toString() //username
//            player.tid = "" //placeholder for tag
//            player.groupId = createGroupCode.text.toString() //group the player is in
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