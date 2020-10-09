package com.toturials.foodviet.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.toturials.foodviet.Helpers
import com.toturials.foodviet.welcome.onboardingfragments.FragmentOnBoarding1
import com.toturials.foodviet.welcome.onboardingfragments.FragmentOnBoarding2
import com.toturials.foodviet.welcome.onboardingfragments.FragmentOnBoarding3

class SliderOnboardingAdapter(context:Context,fm: FragmentManager):FragmentStatePagerAdapter(fm) {
    var context: Context = context
    override fun getCount(): Int {
        return Helpers.NUM_PAGES
    }

    override fun getItem(position: Int): Fragment {
        var fragment:Fragment?=null
        when(position){
            0->
                fragment = FragmentOnBoarding1()
            1->
                fragment = FragmentOnBoarding2()
            2->
                fragment = FragmentOnBoarding3()
        }
        return fragment!!
    }
}