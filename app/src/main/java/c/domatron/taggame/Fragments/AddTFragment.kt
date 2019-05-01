package c.domatron.taggame.Fragments


import android.annotation.SuppressLint
import android.content.Intent
import android.nfc.NfcAdapter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import c.domatron.taggame.R
import c.domatron.taggame.Utilities.NFCUtil
import c.domatron.taggame.Utilities.SQLManager
import c.domatron.taggame.Utilities.getMacAddr
import c.domatron.taggame.Utilities.tagGet
import kotlinx.android.synthetic.main.activity_add_tag.*
import kotlinx.android.synthetic.main.fragment_add_t.*
import org.jetbrains.anko.toast
import kotlin.concurrent.thread

/* Author: Dominic Triano
 * Date: 2/5/2019
 * Language: Kotlin
 * Project: TagGame
 * Description:
 * This fragment displays the current registered tag and gives the option to update or add one
 * however, there is only one tag per person allowed at a time
 *
 */

class AddTFragment : Fragment() {

    private lateinit var dbHandler : SQLManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dbHandler = SQLManager(context!!)

        println("$tag")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_add_t, container, false)


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initialize fab button
        setupAddButton()
        //setup database
        val dbHandler = SQLManager(context!!)

        //val tag = dbHandler.getTag()
        val tag = tagGet(getMacAddr())

        if(tag != "")
        {
            registerTitle.text = "Your tag has already been set! \n" +
                    "If you would like to change/update it, press the button below\n" +
                    "Your current tag is $tag."
        }
    }

    fun setupAddButton(){
        addTagFAB.setOnClickListener{view -> addTag()}
    }

    fun addTag()
    {
        val intent = Intent(activity, AddTagActivity::class.java)
        startActivity(intent)
    }

    companion object {
        fun newInstance(): AddTFragment = AddTFragment()
    }
}
