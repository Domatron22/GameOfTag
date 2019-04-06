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
    val wifiManager = activity?.applicationContext?.getSystemService(Context.WIFI_SERVICE) as WifiManager
    val wInfo = wifiManager.connectionInfo
    val macAddress = wInfo.macAddress

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?) : View?
    {

        activity?.homeTitle?.text = "Welcome Back ${getUName()}"

        val view: View = inflater!!.inflate(R.layout.fragment_home, container, false)

        tagEnable.setOnClickListener()
        {
            view ->
            Log.d("btnSetup", "Selected")
        }

        return view
    }

    fun getUName() : String {
        return activity?.database?.use{
            select("Group", "user").whereArgs("macAddrs = $macAddress")
        }.toString()
    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }


}
