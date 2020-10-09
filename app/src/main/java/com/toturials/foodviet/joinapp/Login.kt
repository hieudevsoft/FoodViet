package com.toturials.foodviet.joinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.toturials.foodviet.Helpers.hideSystemUI
import com.toturials.foodviet.Helpers.initAnimationX
import com.toturials.foodviet.Helpers.initAnimationY
import com.toturials.foodviet.Helpers.setAnimationX
import com.toturials.foodviet.Helpers.setAnimationY
import com.toturials.foodviet.R
import com.toturials.foodviet.adapter.LoginAdapter
import kotlinx.android.synthetic.main.layout_login.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemUI()
        setContentView(R.layout.layout_login)
        setTabLayout()
        setViewPagerLogin()
        animationView()
    }
    private fun setTabLayout(){
        tablayout_Login.addTab(tablayout_Login.newTab().setText("Login"))
        tablayout_Login.addTab(tablayout_Login.newTab().setText("SignUp"))
        tablayout_Login.tabGravity = TabLayout.GRAVITY_FILL
    }
    private fun setViewPagerLogin()
    {
        val adapter = LoginAdapter(this,supportFragmentManager,tablayout_Login.tabCount)
        viewPagerLogin.adapter = adapter
        viewPagerLogin.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tablayout_Login))
        viewPagerLogin.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
               (viewPagerLogin.adapter as LoginAdapter).notifyDataSetChanged()
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })
        tablayout_Login.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(p0: TabLayout.Tab?) {
                viewPagerLogin.currentItem = p0!!.position
                (viewPagerLogin.adapter as LoginAdapter).notifyDataSetChanged()
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

        })
    }


    private fun animationView()
    {
        initAnimationY(fab_Facebook,300F)
        initAnimationY(fab_Google,300F)
        initAnimationX(tablayout_Login,800F)

        setAnimationY(fab_Facebook,1000,1300)
        setAnimationY(fab_Google,1000,1500)
        setAnimationX(tablayout_Login,200,500)
    }

}