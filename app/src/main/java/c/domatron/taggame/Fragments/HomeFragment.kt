package c.domatron.taggame.Fragments


import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import c.domatron.taggame.Utilities.database
import c.domatron.taggame.R
import c.domatron.taggame.Utilities.SQLManager
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.experimental.selects.select
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

/**
 * Fragment for the home tab with basic updates for the players
 *
 */
class HomeFragment : Fragment() {

    private lateinit var hTitle: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Get macAddress, the unique identifier for the table
        val wifiManager = activity?.applicationContext?.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wInfo = wifiManager.connectionInfo
        val macAddress = wInfo.macAddress
        val dbHandler = SQLManager(if (getContext() != null) getContext()!! else throw NullPointerException("Expression 'getContext()' must not be null"))

//        val uName = activity?.database?.use {
//            select("Group", "user").whereArgs("macAddrs = $macAddress")
//        }

        //title in the home fragment
        val uName = dbHandler!!.getUser()
        hTitle.setText("Welcome back " + uName)

        tagEnable.setOnClickListener()
        {
            //TODO -- Enable NFC and Listen for a tag
            view -> Log.d("btnSetup", "Selected")
        }
    }


    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }


}
