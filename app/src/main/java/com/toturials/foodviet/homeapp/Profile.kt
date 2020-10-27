package com.toturials.foodviet.homeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.toturials.foodviet.Helpers.hideSystemUI
import com.toturials.foodviet.R
import kotlinx.android.synthetic.main.layout_content_profiles.*
import kotlinx.android.synthetic.main.layout_profile.*

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemUI()
        setContentView(R.layout.layout_profile)
        btn_UpdateProfile.setOnClickListener {
            finish()
        }
        tv_BackToHome.setOnClickListener {
            finish()
        }
    }

}