package com.toturials.foodviet.joinapp.joinappfragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.toturials.foodviet.Helpers
import com.toturials.foodviet.R
import com.toturials.foodviet.joinapp.Login
import kotlinx.android.synthetic.main.layout_login_tablayout.view.*
import kotlinx.android.synthetic.main.layout_onboarding_tab1.view.*

class TabLoginFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_login_tablayout,container,false)
        Helpers.initAnimationX(view.edt_EmailLogin,800F)
        Helpers.initAnimationX(view.edt_PasswordLogin,800F)
        Helpers.initAnimationX(view.button_Login,800F)
        Helpers.initAnimationX(view.tv_ForgotPassword,800F)
        Helpers.setAnimationX(view.edt_EmailLogin,800,200)
        Helpers.setAnimationX(view.edt_PasswordLogin,800,400)
        Helpers.setAnimationX(view.tv_ForgotPassword,800,400)
        Helpers.setAnimationX(view.button_Login,800,600)
        return view
    }
}