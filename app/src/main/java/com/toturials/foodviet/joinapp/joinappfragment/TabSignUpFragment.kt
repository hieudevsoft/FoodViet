package com.toturials.foodviet.joinapp.joinappfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.toturials.foodviet.Helpers
import com.toturials.foodviet.R
import kotlinx.android.synthetic.main.layout_signup_tablayout.view.*

class TabSignUpFragment : Fragment() {
    final val TAG = "TabSignUpFragment"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            LayoutInflater.from(context).inflate(R.layout.layout_signup_tablayout, container, false)
        Helpers.initAnimationX(view.edt_EmailSignUp, 800F)
        Helpers.initAnimationX(view.edt_PasswordSignUp, 800F)
        Helpers.initAnimationX(view.button_SignUp, 800F)
        Helpers.initAnimationX(view.edt_ConfirmPasswordSignUp, 800F)
        Helpers.initAnimationX(view.edt_PhoneSignUp, 800F)
        Helpers.setAnimationX(view.edt_EmailSignUp, 800,100)
        Helpers.setAnimationX(view.edt_PasswordSignUp, 800,200)
        Helpers.setAnimationX(view.edt_ConfirmPasswordSignUp, 800,300)
        Helpers.setAnimationX(view.edt_PhoneSignUp, 800,400)
        Helpers.setAnimationX(view.button_SignUp, 800,500)
        return view
    }
}