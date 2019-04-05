package c.domatron.taggame.Fragments


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
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.toast

/**
 * Fragment for the home tab with basic updates for the players
 *
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?) : View?
    {

        val view: View = inflater!!.inflate(R.layout.fragment_home, container, false)

        tagEnable.setOnClickListener()
        {
            view ->
            Log.d("btnSetup", "Selected")
        }

        return view
    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }


}
