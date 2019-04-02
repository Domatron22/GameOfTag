package c.domatron.taggame.Fragments

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import c.domatron.taggame.R
import kotlinx.android.synthetic.main.activity_join_group.*

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
        //TODO: add if/when to make sure there is a username and group code
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}
