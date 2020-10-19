package com.toturials.foodviet.joinapp.joinappfragment

import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.toturials.foodviet.Helpers
import com.toturials.foodviet.R
import com.toturials.foodviet.joinapp.Login
import kotlinx.android.synthetic.main.layout_login.*
import kotlinx.android.synthetic.main.layout_login_tablayout.*
import kotlinx.android.synthetic.main.layout_login_tablayout.view.*
import kotlinx.android.synthetic.main.layout_onboarding_tab1.view.*

class TabLoginFragment:Fragment() {
    final val TAG = "TabLoginFragment"
    lateinit var firebaseAuth:FirebaseAuth
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        firebaseAuth = FirebaseAuth.getInstance()
        loginWithEmail()
        super.onViewCreated(view, savedInstanceState)
    }
    private fun loginWithEmail() {

        view?.button_Login?.setOnClickListener {
            val dialog = Helpers.DialogLoading(requireContext())
            val email = view?.edt_EmailLogin?.text.toString().trim()
            val password = view?.edt_PasswordLogin?.text.toString().trim()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                Log.d(TAG, "loginWithEmail: Email: $email Pass: $password")
                dialog.show()
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    dialog.dismiss()
                    view?.button_Login?.visibility = View.VISIBLE
                    if (it.isSuccessful)
                        Toast.makeText(requireContext(), "Login Successful~", Toast.LENGTH_SHORT)
                            .show()
                    else {
                        Log.d(TAG, "signUpWithFirebase: ${it.exception?.message}")
                        val snackbar =
                            Snackbar.make(requireView(), "Email or password not exits", 2000)
                        snackbar.setAction("DISSMISS") {
                            snackbar.dismiss()
                        }
                        snackbar.show()
                    }
                }
            } else{
                val snackbar =
                    Snackbar.make(requireView(), "Email and password must be fill out", 2000)
                snackbar.setAction("DISSMISS") {
                    snackbar.dismiss()
                }
                snackbar.show()
            }
        }
    }
}