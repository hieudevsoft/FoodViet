package com.toturials.foodviet.joinapp.joinappfragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.toturials.foodviet.Helpers
import com.toturials.foodviet.R
import com.toturials.foodviet.homeapp.MainApp
import kotlinx.android.synthetic.main.layout_signup_tablayout.*
import kotlinx.android.synthetic.main.layout_signup_tablayout.view.*
import java.util.*

class TabSignUpFragment : Fragment() {
    final val TAG = "TabSignUpFragment"
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var authStateListener:FirebaseAuth.AuthStateListener
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
        Helpers.setAnimationX(view.edt_EmailSignUp, 800, 100)
        Helpers.setAnimationX(view.edt_PasswordSignUp, 800, 200)
        Helpers.setAnimationX(view.edt_ConfirmPasswordSignUp, 800, 300)
        Helpers.setAnimationX(view.edt_PhoneSignUp, 800, 400)
        Helpers.setAnimationX(view.button_SignUp, 800, 500)
        firebaseAuth = FirebaseAuth.getInstance()
        return view
    }
    private fun checkSignUp(): Boolean {
        var check_email = false
        var check_password = false
        var check_confirm_password = false
        var check_phone = false

        view?.edt_EmailSignUp?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!Helpers.isValidEmail(s.toString()))
                    edt_EmailSignUp.error = "Email invalid!"
                else check_email = true
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        view?.edt_PasswordSignUp?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().length < 8)
                    edt_PasswordSignUp.error = "Minimum password is 8 character"
                else check_confirm_password = true
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        view?.edt_ConfirmPasswordSignUp?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().trim() == edt_PasswordSignUp.text.toString().trim())
                    check_confirm_password = true
                else edt_ConfirmPasswordSignUp.error = "Password is not matcher"
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        view?.edt_PhoneSignUp?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isEmpty()) edt_PhoneSignUp.error = "Please enter the phone number"
                else check_phone = true
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        val email = view?.edt_EmailSignUp?.text.toString().trim()
        check_email = Helpers.isValidEmail(email)
        val password = view?.edt_PasswordSignUp?.text.toString().trim()
        check_password = password.length >= 8
        val confirmPassword = view?.edt_ConfirmPasswordSignUp?.text.toString().trim()
        check_confirm_password = password == confirmPassword
        if(!check_confirm_password) edt_ConfirmPasswordSignUp.error = "Password is not matcher"
        val phone = view?.edt_PhoneSignUp?.text.toString().trim()
        check_phone = phone.isNotEmpty()
        Log.d(TAG, "checkSignUp: check : $check_email $check_password $check_confirm_password $check_phone")
        return (check_email && check_password && check_confirm_password && check_phone)

    }
    private fun createAccount() {
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                activity?.runOnUiThread {
                    val check = checkSignUp()
                    if (checkSignUp()) {
                        view?.button_SignUp?.setBackgroundResource(R.drawable.custom_login_button)
                    } else {
                        view?.button_SignUp?.setBackgroundResource(R.drawable.custom_login_button_disabled)
                    }
                    Log.d(TAG, "createAccount: $check")
                    view?.button_SignUp?.isEnabled = check
                }
            }
        }, 500, 500)
        view?.button_SignUp?.setOnClickListener {
            timer.cancel()
            signUpWithFirebase()
        }
    }
    override fun onStart() {
        super.onStart()
    }
    override fun onStop() {
        super.onStop()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        createAccount()
        super.onViewCreated(view, savedInstanceState)
    }
    private fun signUpWithFirebase() {
        val dialog = Helpers.DialogLoading(requireContext())
        val email = view?.edt_EmailSignUp?.text.toString().trim()
        val password = view?.edt_PasswordSignUp?.text.toString().trim()
        Log.d(TAG, "signUpWithFirebase: email: $email pass: $password")
        dialog.show()
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            dialog.dismiss()
                if(it.isSuccessful)
                Toast.makeText(context,"Register Successful~",Toast.LENGTH_SHORT).show()
            else {
                    Log.d(TAG, "signUpWithFirebase: ${it.exception?.message}")
                    val snackbar = Snackbar.make(requireView(),"Register falure",2000)
                    snackbar.setAction("DISSMISS") {
                        snackbar.dismiss()
                    }
                    snackbar.show()
                }
        }
    }

}