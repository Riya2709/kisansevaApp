package com.shaw.kisanseva

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class OnBoardingFragment3:Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // return super.onCreateView(inflater, container, savedInstanceState)
        val root= inflater.inflate(R.layout.fragment_on_boarding3,container,false) as ViewGroup
        val next=root.findViewById<FloatingActionButton>(R.id.next)
        val skip=root.findViewById<TextView>(R.id.txt12)
        skip.setOnClickListener(View.OnClickListener {
            val intent=Intent(activity,Register::class.java)
            startActivity(intent)
            activity!!.finish()

        })

        next.setOnClickListener(View.OnClickListener {
            val intent=Intent(activity,Register::class.java)
            startActivity(intent)
            activity!!.finish()

        })

        return root
    }





}