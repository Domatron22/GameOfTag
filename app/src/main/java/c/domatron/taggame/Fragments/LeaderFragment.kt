package c.domatron.taggame.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import c.domatron.taggame.R

/* Author: Dominic Triano
 * Date: 2/5/2019
 * Language: Kotlin
 * Project: TagGame
 * Description:
 * This fragment allows you to keep track of who is in your group and how they are performing
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
