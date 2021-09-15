package com.shaw.kisanseva

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View

class ModalActivity : MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater:LayoutInflater= LayoutInflater.from(this)
        val v: View =inflater.inflate(R.layout.activity_modal,null,false)
        drawerLayout!!.addView(v,0)
        navigationView!!.setCheckedItem(R.id.nav_modal)
    }
}