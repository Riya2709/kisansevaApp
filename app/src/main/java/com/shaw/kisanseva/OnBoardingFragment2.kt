package com.shaw.kisanseva

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class OnBoardingFragment2 :Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root:ViewGroup= inflater.inflate(R.layout.fragment_on_boarding2,container,false) as ViewGroup
        val skip=root.findViewById<TextView>(R.id.txt7)
        skip.setOnClickListener(View.OnClickListener {
            val intent= Intent(activity,Register::class.java)
            startActivity(intent)

        })
        return root
    }
}