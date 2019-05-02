package c.domatron.taggame.Fragments


import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import c.domatron.taggame.R
import c.domatron.taggame.Utilities.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast
/* Author: Dominic Triano
 * Date: 2/5/2019
 * Language: Kotlin
 * Project: TagGame
 * Description:
 *
 *
 */

class HomeFragment : Fragment() {

//    private lateinit var hTitle: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_home, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
//        val uName = activity?.database?.use {
//            select("Group", "user").whereArgs("macAddrs = $macAddress")
//        }

        //title in the home fragment
        val uName = chkUser(getMacAddr())
        homeTitle.setText("Welcome back $uName")

        //Finds the players status and tells the player accordingly
        val status = chkStatus(uName)

        if(status == "1")
        {
            homeStatus.setText("You are currently it!\nThe clock is ticking, find your opponent.")
        }else{
            homeStatus.setText("You are currently not it.\nHowever, be on the lookout for bloodthursty teammates.")
        }
    }

    fun setupButtons()
    {
        tagEnable.setOnClickListener { view -> tagEnable() }
    }

    fun tagEnable()
    {
        //TODO -- Finish the NFC read object and have it read the tag then set your status to 0 and theirs to 1
        val intent = Intent(activity, MainActivity::class.java)
        Toast.makeText(activity, "Scanning for Tag, get in range!", Toast.LENGTH_LONG).show()
        Toast.makeText(activity, NFCUtil.retrieveNFCMessage(intent), Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }
}
