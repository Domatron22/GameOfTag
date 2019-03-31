package c.domatron.taggame

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import android.arch.persistence.room.Query
import android.widget.Button
import android.widget.Toast


class LogIn : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.activity_log_in, container, false)

    companion object {
        fun newInstance(): LogIn = LogIn()
    }

    

}
