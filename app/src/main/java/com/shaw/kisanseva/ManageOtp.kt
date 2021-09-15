package com.shaw.kisanseva

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.concurrent.TimeUnit


class ManageOtp : AppCompatActivity() {
    //var mAuth: FirebaseAuth? = null
    var otpId:String?=null
    private var mAuth:FirebaseAuth?=null
    private var database= FirebaseDatabase.getInstance()
    private var myRef=database.reference
    var phoneNum:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {

       // var mAuth: FirebaseAuth? = null
       mAuth= FirebaseAuth.getInstance()



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_otp)
        val t2=findViewById<EditText>(R.id.t2)
        val bu2=findViewById<Button>(R.id.bu2)
         phoneNum=intent.getStringExtra("mobile").toString()

        initiateOtp(phoneNum!!)
        bu2.setOnClickListener(View.OnClickListener {
            if(t2.text.toString().isEmpty()){
                Toast.makeText(applicationContext,"Blank Field can not br processed",Toast.LENGTH_LONG).show()
            }
            else if(t2.text.toString().length!=6){
                Toast.makeText(applicationContext,"Invalid OTP",Toast.LENGTH_LONG).show()
            }
            else{
                val credential:PhoneAuthCredential=PhoneAuthProvider.getCredential(otpId!!,t2.text.toString())
                signInWithPhoneAuthCredential(credential)
            }

        })
    }
    //var mAuth:FirebaseAuth= FirebaseAuth.getInstance()

    fun initiateOtp(phoneNum:String){
        lateinit var callbacks:OnVerificationStateChangedCallbacks
        callbacks=object :PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                signInWithPhoneAuthCredential(p0)
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                otpId=p0

            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Toast.makeText(applicationContext,p0.message,Toast.LENGTH_LONG).show()
            }

        }


        val options = PhoneAuthOptions.newBuilder(mAuth!!)
            .setPhoneNumber(phoneNum)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)// OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth!!.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    checkPhoneNum(phoneNum!!)
                   // startActivity(Intent(this,MainActivity::class.java))
                   // finish()




                } else {
                    Toast.makeText(applicationContext,"Sometheing Wrong",Toast.LENGTH_LONG).show()


                }
            }
    }
    fun checkPhoneNum(PhoneNumber:String){

        myRef.orderByChild("phone").equalTo(phoneNum).addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")

            }
            override fun onDataChange(snapshot: DataSnapshot) {
                if(!snapshot.exists()){
                    myRef.child("phone").setValue(PhoneNumber)
                    startActivity(Intent(applicationContext,HomeActivity::class.java))
                    finish()

                }
                else{
                    startActivity(Intent(applicationContext,HomeActivity::class.java))
                    finish()


                }
            }

        })






}
}