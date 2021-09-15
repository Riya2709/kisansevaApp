package com.shaw.kisanseva

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SchemesInfo : AppCompatActivity() {
    var title:String?=null
     var listofSchemes=ArrayList<SchemesDesc>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schemes_info)
        val bundle:Bundle?=intent.extras
        title=bundle!!.getString("title")
        findViewById<TextView>(R.id.SchemeTitle).setText(title)
        val desc=findViewById<TextView>(R.id.descS)
        val link=findViewById<TextView>(R.id.link)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://kisan-seva.herokuapp.com/agriinfo/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(SchemeService2::class.java)
        val call = service.getInfo(title.toString())
        call.enqueue(object :Callback<ArrayList<SchemesDesc>>{
            override fun onResponse(
                call: Call<ArrayList<SchemesDesc>>?,
                response: Response<ArrayList<SchemesDesc>>?
            ){
                if (response!!.code()!=200) {
                    Toast.makeText(applicationContext,response.code(),Toast.LENGTH_LONG).show()
                }
                else{
                    listofSchemes=response.body()
                    desc.setText(listofSchemes[0].desc)
                    link.setText(listofSchemes[0].link)
                }
            }

            override fun onFailure(call: Call<ArrayList<SchemesDesc>>?, t: Throwable?) {
                if (t != null) {
                    Toast.makeText(applicationContext,t.message,Toast.LENGTH_LONG).show()
                }
            }

        })
        link.movementMethod=LinkMovementMethod.getInstance()
        link.setOnClickListener {
            val intent=Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(link.text.toString()))
            startActivity(intent)
        }












    }
}