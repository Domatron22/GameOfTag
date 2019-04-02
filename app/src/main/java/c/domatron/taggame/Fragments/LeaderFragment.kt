package c.domatron.taggame.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import c.domatron.taggame.R

/**
 *
 * Fragment for the Leaderboards:
 * Here the high scores for your group will be displayed
 * tag count, time being 'it', etc.
 *
 */
class LeaderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_leader, container, false)

    companion object {
        fun newInstance(): LeaderFragment = LeaderFragment()
    }

    /*TODO -- reference the web database to see who has been tagged the most/least times*/

}
