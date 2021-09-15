package com.shaw.kisanseva

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.airbnb.lottie.LottieAnimationView
import com.cuberto.liquid_swipe.LiquidPager
import kotlin.properties.Delegates

class IntoductoryActivity : AppCompatActivity() {
    var numpage:Int=3


    override fun onCreate(savedInstanceState: Bundle?) {

        var pagerAdapter:ScreenPageAdapter?=null
        var anim:Animation?=null
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intoductory)
        window.setFlags(

                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val logo=findViewById<ImageView>(R.id.logo_img)
        val splash=findViewById<ImageView>(R.id.into_img)
        val texti=findViewById<TextView>(R.id.name_txt)
        val tractor=findViewById<LottieAnimationView>(R.id.tractor)

        pagerAdapter=ScreenPageAdapter(supportFragmentManager)
        findViewById<LiquidPager>(R.id.pager).adapter=pagerAdapter
        anim=AnimationUtils.loadAnimation(this,R.anim.o_b_anim)
        findViewById<LiquidPager>(R.id.pager).startAnimation(anim)




        splash.animate().translationY((-1600).toFloat()).setDuration(1000).setStartDelay(4000)
        logo.animate().translationY((1400).toFloat()).setDuration(1000).setStartDelay(4000)
        texti.animate().translationY((1400).toFloat()).setDuration(1000).setStartDelay(4000)
        tractor.animate().translationY((1400).toFloat()).setDuration(1000).setStartDelay(4000)
    }

     inner class ScreenPageAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        //var context:Context?=null
        // var fm:FragmentManager?=null




         override fun getItem(position: Int): Fragment {
            if(position==0){ val tab1=OnBoardingFragment1()
                    return tab1}
            else if(position==1) {
                val tab2 = OnBoardingFragment2()
                return tab2
            }
            else {
                    val tab3=OnBoardingFragment3()
                    return tab3

            }




        }

        override fun getCount(): Int {
            return numpage
        }

    }


}