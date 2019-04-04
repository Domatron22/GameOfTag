package c.domatron.taggame.Fragments

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import c.domatron.taggame.R
import kotlinx.android.synthetic.main.activity_create_group.*

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

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}