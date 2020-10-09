package com.toturials.foodviet.welcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.toturials.foodviet.Helpers.hideSystemUI
import com.toturials.foodviet.R
import com.toturials.foodviet.adapter.SliderOnboardingAdapter
import kotlinx.android.synthetic.main.layout_splash.*

class SplashScreen : AppCompatActivity() {
    lateinit var sliderOnboardingAdapter: SliderOnboardingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemUI()
        setContentView(R.layout.layout_splash)
        img_backGround.animate().translationY(-1800F).setDuration(1200).startDelay = 4400
        img_Logo.animate().translationY(1400F).setDuration(1200).startDelay = 4400
        tv_AppName.animate().translationY(1400F).setDuration(1200).startDelay = 4400
        lottie_Logo.animate().translationY(1400F).setDuration(1200).startDelay = 4400
        setOnBoarding()
    }
    private fun setOnBoarding()
    {
        sliderOnboardingAdapter = SliderOnboardingAdapter(this,supportFragmentManager)
        viewPager.adapter = sliderOnboardingAdapter
        viewPager.animation = AnimationUtils.loadAnimation(this,R.anim.anim_onboarding)
    }

}