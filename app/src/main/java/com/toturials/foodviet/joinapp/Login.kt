package com.toturials.foodviet.joinapp

import NetworkReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.facebook.*
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.toturials.foodviet.Helpers.hideSystemUI
import com.toturials.foodviet.Helpers.initAnimationX
import com.toturials.foodviet.Helpers.initAnimationY
import com.toturials.foodviet.Helpers.setAnimationX
import com.toturials.foodviet.Helpers.setAnimationY
import com.toturials.foodviet.NetworkUtil
import com.toturials.foodviet.R
import com.toturials.foodviet.adapter.LoginAdapter
import com.toturials.foodviet.homeapp.MainApp
import kotlinx.android.synthetic.main.layout_login.*
import kotlinx.android.synthetic.main.layout_login_tablayout.*
import kotlinx.android.synthetic.main.layout_signup_tablayout.view.*
import kotlin.math.log

class Login : AppCompatActivity() {
    val TAG = "Login"
    lateinit var callBackManager: CallbackManager
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var authStateListener: FirebaseAuth.AuthStateListener
    lateinit var accessTokenTracker: AccessTokenTracker
    lateinit var googleSignInClient:GoogleSignInClient
    lateinit var networkReceiver: NetworkReceiver
    private val RC_SIGN_IN = 111
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemUI()
        setContentView(R.layout.layout_login)
        firebaseAuth = FirebaseAuth.getInstance()
        setTabLayout()
        setViewPagerLogin()
        animationView()
        networkReceiver = NetworkReceiver()
        try {
            registerReceiver(networkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }catch (e:Exception){}
        if(NetworkUtil.getConnectivityStatusString(this)!!.isNotEmpty()) {
            try {
                loginWithFaceBook()
                loginWithGoogle()
            }catch (e:Exception){

            }

        }
        authStateListener = FirebaseAuth.AuthStateListener {
            val user = firebaseAuth.currentUser
            if (user != null)
                updateUI(user)
        }
    }
    private fun loginWithGoogle() {
        createRequestGoogle()
        fab_Google.setOnClickListener {
            val intent = googleSignInClient.signInIntent
            startActivityForResult(intent,RC_SIGN_IN)
        }

    }
    private fun createRequestGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,gso)

    }
    private fun handlerGoogleSignIn(account: GoogleSignInAccount?) {
        val credential = GoogleAuthProvider.getCredential(account?.idToken,null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful)
                Log.d(TAG, "handlerGoogleSignIn: Login with Google Successful")
            else Log.d(TAG, "handlerGoogleSignIn: Login with Google Failed")
        }

    }
    private fun loginWithFaceBook() {
        FacebookSdk.sdkInitialize(this)
        callBackManager = CallbackManager.Factory.create()
        login_button_facebook.setReadPermissions("email", "public_profile")
        login_button_facebook.registerCallback(
            callBackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    Log.d(TAG, "onSuccess: onSuccessFacebook + ${result.toString()}")
                    handlerFacebookAccessToken(result?.accessToken)
                }

                override fun onCancel() {
                    Log.d(TAG, "onCancel: onCancelFacebook")
                }

                override fun onError(error: FacebookException?) {
                    Log.d(TAG, "onError: onErrorFacebook ${error?.message}")
                }

            })
        accessTokenTracker = object : AccessTokenTracker() {
            override fun onCurrentAccessTokenChanged(
                oldAccessToken: AccessToken?,
                currentAccessToken: AccessToken?
            ) {
                if (currentAccessToken == null)
                    firebaseAuth.signOut()
            }

        }
    }
    private fun handlerFacebookAccessToken(accessToken: AccessToken?) {
        Log.d(TAG, "handlerFacebookAccessToken: Accesstoken $accessToken")
        val authCredential = FacebookAuthProvider.getCredential(accessToken?.token.toString())
        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(
            this
        ) {
            if (it.isSuccessful) {
                Log.d(TAG, "handlerFacebookAccessToken: Login with Facebook Successful")
            } else {
                Log.d(TAG, "handlerFacebookAccessToken: Login with Facebook Failed")
            }
        }


    }
    private fun updateUI(user: FirebaseUser?) {
        user?.let {
            Intent(this, MainApp::class.java).also {
                if (user.photoUrl != null)
                    it.putExtra("photoURI", user.photoUrl.toString())
                else it.putExtra(
                    "photoURI",
                    "https://i0.wp.com/eikongroup.co.uk/wp-content/uploads/2017/04/Blank-avatar.png"
                )
                startActivity(it)
            }
        }
    }
    private fun setTabLayout() {
        tablayout_Login.addTab(tablayout_Login.newTab().setText("Login"))
        tablayout_Login.addTab(tablayout_Login.newTab().setText("SignUp"))
        tablayout_Login.tabGravity = TabLayout.GRAVITY_FILL
    }
    private fun setViewPagerLogin() {
        val adapter = LoginAdapter(this, supportFragmentManager, tablayout_Login.tabCount)
        viewPagerLogin.adapter = adapter
        viewPagerLogin.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                tablayout_Login
            )
        )
        viewPagerLogin.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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
        tablayout_Login.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
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
    private fun animationView() {
        initAnimationY(fab_Facebook, 300F)
        initAnimationY(fab_Google, 300F)
        initAnimationX(tablayout_Login, 800F)

        setAnimationY(fab_Facebook, 1000, 1300)
        setAnimationY(fab_Google, 1000, 1500)
        setAnimationX(tablayout_Login, 200, 500)
    }
    override fun onStart() {
        firebaseAuth.addAuthStateListener(authStateListener)
        super.onStart()
    }
    override fun onPause() {
        try {
            unregisterReceiver(networkReceiver)
        }catch (e:Exception){

        }

        super.onPause()
    }
    override fun onStop() {
        firebaseAuth.removeAuthStateListener(authStateListener)
        super.onStop()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callBackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==RC_SIGN_IN){
            Log.d(TAG, "onActivityResult: Login with Google")
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                handlerGoogleSignIn(account)
            }catch (e:ApiException){
                Log.d(TAG, "onActivityResult: Failed sign in with google  ${e.message}")
            }
        }
    }

}