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
 * This fragment allows a user to tag another individual, However, it was taken out and merged with the home fragment
 * in the newest update (4/14/2019)
 *
 */

class TagFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_tag, container, false)

    companion object {
        fun newInstance(): TagFragment = TagFragment()
    }

    /*TODO -- Update users status'*/

}
