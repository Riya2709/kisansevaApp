package com.shaw.kisanseva

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.hbb20.CountryCodePicker
import com.google.firebase.database.ValueEventListener as ValueEventListener1

class Register : AppCompatActivity() {
    private var mAuth:FirebaseAuth?=null
    private var database= FirebaseDatabase.getInstance()
    private var myRef=database.reference
    var mobileNum:EditText?=null
    var ccp:CountryCodePicker?=null
    var login:Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mAuth= FirebaseAuth.getInstance()
         mobileNum=findViewById(R.id.mobileNum)
         ccp=findViewById(R.id.ccp)
         login=findViewById(R.id.login_but)
        ccp!!.registerCarrierNumberEditText(mobileNum)
        login!!.setOnClickListener {
            val intent = Intent(this, ManageOtp::class.java)
            intent.putExtra("mobile",ccp!!.fullNumberWithPlus.replace(" ",""))
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()



        }


    }


    override fun onStart() {

        super.onStart()
        val currentUser=mAuth!!.currentUser
        if(currentUser!=null) {
            val intent = Intent(applicationContext, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}