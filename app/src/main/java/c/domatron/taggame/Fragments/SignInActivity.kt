package c.domatron.taggame.Fragments

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
import kotlinx.android.synthetic.main.activity_sign_in.*




class SignInActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        initView()

    }


   private fun initView(){
       JoinGame.setOnClickListener { view -> showJoin() }
       CreateGame.setOnClickListener { view -> showCreate() }
   }

    private fun showJoin(){
//        Moving to separate fragment instead of alertBoxes
//        val alertDialogBox = AlertDialog.Builder(this)
//        var roomCode : EditText?=null
//        alertDialogBox.setTitle("Join Room")
//        alertDialogBox.setMessage("$uname, Enter your Room Code:")
//
//
//        roomCode = EditText(this)
//        roomCode!!.hint="Code Number"
//        roomCode!!.inputType = InputType.TYPE_CLASS_NUMBER
//
//        alertDialogBox.setPositiveButton("OK") {
//                dialog, whichButton ->
//            if(/*table is marked as open or exists*/) {
//                alertDialogBox.setMessage("You have joined the group")
//                dialog.dismiss()
//                var rmc = roomCode!!.text.toString()
//                //adds username to the roomcode in the database
//            }else{
//                alertDialogBox.setMessage("Room Code not found, Please try again")
//            }
            val intent = Intent(this, JoinGroupActivity::class.java)
            startActivity(intent)
//        }

//        alertDialogBox.setNegativeButton("Cancel") {
//                dialog, whichButton ->
//            dialog.dismiss()
//        }
    }

    private fun showCreate(){

    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }




}
