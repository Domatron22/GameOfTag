package c.domatron.taggame.Fragments

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.InputType
import android.view.View
import android.widget.EditText
import c.domatron.taggame.DAO.PlayerDao
import c.domatron.taggame.DAO.TagDatabase
import c.domatron.taggame.R
import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import c.domatron.taggame.Utilities.chkUser
import c.domatron.taggame.Utilities.getMacAddr
import kotlinx.android.synthetic.main.activity_sign_in.*


/* Author: Dominic Triano
 * Date: 4/3/2019
 * Language: Kotlin
 * Project: TagGame
 * Description:
 * This activity redirects the user to the correct Activity based on their needs
 *
 */

class SignInActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        if(chkUser(getMacAddr())!!.isNotEmpty())
        {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        initView()
    }

    private fun initView(){
        JoinGame.setOnClickListener { view -> showJoin() }
        CreateGame.setOnClickListener { view -> showCreate() }
    }

    private fun showJoin(){
        //Opens join Activity
        val intent = Intent(this, JoinGroupActivity::class.java)
        startActivity(intent)
    }

    private fun showCreate(){
        //Opens create Activity
        val intent = Intent(this, CreateGroupActivity::class.java)
        startActivity(intent)
    }

//    fun checkUser()
//    {
//        val mac = getMacAddr()
//        val user : String = chkUser(mac)
//
//        //If the user is already registered
//        if(user != "Cannot Find User" && user != "Failed To Connect")
//        {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
//    }
}
