package c.domatron.taggame.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import c.domatron.taggame.R

/**
 * Fragment for the Tag Tab to enable the player to tag another players tag.
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
