package c.domatron.taggame.Fragments


import android.content.Intent
import android.nfc.NfcAdapter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import c.domatron.taggame.R
import c.domatron.taggame.Utilities.NFCUtil
import org.jetbrains.anko.toast

/**
 * Fragment to display the player's current active tag, If there are no active tags,
 * it takes them to a separate activity to register their tag
 *
 */
class AddTFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_add_t, container, false)

    companion object {
        fun newInstance(): AddTFragment = AddTFragment()
    }
}
