package com.shaw.kisanseva

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class SchemesActivity : MainActivity() {

    var listofSchemes=ArrayList<Schemes>()
    var adapter:SchemesAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater: LayoutInflater = LayoutInflater.from(this)
        val v: View =inflater.inflate(R.layout.activity_schemes,null,false)
        drawerLayout!!.addView(v,1)
        navigationView!!.setCheckedItem(R.id.nav_Schemes)



        val retrofit = Retrofit.Builder()
                .baseUrl("https://kisan-seva.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(SchemeService::class.java)
        val call = service.getTitle()
        call.enqueue(object : Callback<ArrayList<Schemes>> {
            override fun onResponse(call: Call<ArrayList<Schemes>>, response: Response<ArrayList<Schemes>>) {
                if (response.code()!=200) {
                    Toast.makeText(applicationContext,response.code(),Toast.LENGTH_LONG).show()
                }
                else{
                    listofSchemes=response.body()
                    adapter= SchemesAdapter(this@SchemesActivity,listofSchemes)
                    val d=findViewById<ListView>(R.id.schemelist)
                    d.adapter=adapter


                }


            }

            override fun onFailure(call: Call<ArrayList<Schemes>>?, t: Throwable?) {
                if (t != null) {
                    Toast.makeText(applicationContext,t.message,Toast.LENGTH_LONG).show()
                }
            }


        })









    }
    class SchemesAdapter: BaseAdapter {
        var listofScheme:ArrayList<Schemes>?=null
        var context: Context?=null
        constructor(context: Context, listofScheme:ArrayList<Schemes>):super(){
            this.listofScheme=listofScheme
            this.context=context

        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val scheme=listofScheme!![position]
            val inflator=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val myView=inflator.inflate(R.layout.schemes_ticket,null)
            val title=myView.findViewById<TextView>(R.id.textS)
            title.text=scheme.title
            title.setOnClickListener {
                val intent=Intent(context,SchemesInfo::class.java)
                intent.putExtra("title",title!!.text.trim())
                context!!.startActivity(intent)
            }
            return myView


        }

        override fun getItem(position: Int): Any {
            return listofScheme!![position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
           return listofScheme!!.size
        }
    }
}