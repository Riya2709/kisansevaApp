package com.shaw.kisanseva

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

open class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var drawerLayout:DrawerLayout?=null
    var navigationView:NavigationView?=null
    var toolbar:Toolbar?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout=findViewById(R.id.drawer_layout)
        navigationView=findViewById(R.id.nav_view)
        toolbar=findViewById(R.id.toolbar)




        setSupportActionBar(toolbar)

        navigationView!!.bringToFront()
        var toogle=ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawerLayout!!.addDrawerListener(toogle)
        toogle.syncState()
        navigationView!!.setNavigationItemSelectedListener(this)

        //navigationView!!.setCheckedItem(R.id.nav_home)



    }



    override fun onBackPressed() {
        if (drawerLayout!!.isDrawerOpen((GravityCompat.START))){
            drawerLayout!!.closeDrawer(GravityCompat.START)
        }
        else{
            super.onBackPressed()
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home->{
                val intent=Intent(this,HomeActivity::class.java)
                startActivity(intent)
                finish()


            }
            R.id.nav_modal->{
                val intent=Intent(this,ModalActivity::class.java)
                startActivity(intent)
                finish()

            }
            R.id.nav_Schemes->{
                val intent=Intent(this,SchemesActivity::class.java)
                startActivity(intent)
                finish()
            }
            else->{}
        }
        drawerLayout!!.closeDrawer(GravityCompat.START)

        return true
    }
}

