package com.toturials.foodviet.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.toturials.foodviet.joinapp.joinappfragment.TabLoginFragment
import com.toturials.foodviet.joinapp.joinappfragment.TabSignUpFragment

class LoginAdapter(context:Context, fm: FragmentManager,totalsTab:Int):FragmentStatePagerAdapter(fm) {
    var context: Context = context
    var totalsTab:Int = totalsTab
    override fun getCount(): Int {
        return totalsTab
    }

    override fun getItem(position: Int): Fragment {
        var fragment:Fragment?=null
        when(position){
            0->
                fragment = TabLoginFragment()
            1->
                fragment = TabSignUpFragment()

        }
        return fragment!!
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

}