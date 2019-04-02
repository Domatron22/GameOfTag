package c.domatron.taggame.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import c.domatron.taggame.R

/**
 * Fragment to add tags to your player name so it can be synced with the website
 *
 */
class AddTFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_add_t, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    companion object {
        fun newInstance(): AddTFragment = AddTFragment()
    }

    /*TODO -- Scan a tag and add it to the entry for the user in the database*/

}
