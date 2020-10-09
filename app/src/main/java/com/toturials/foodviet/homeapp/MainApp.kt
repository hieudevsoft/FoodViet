package com.toturials.foodviet.homeapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.toturials.foodviet.Helpers.hideSystemUI
import com.toturials.foodviet.R


class MainApp : AppCompatActivity() {
    final val TAG = "MainApp"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_main_app)

    }
    override fun onBackPressed() {

    }

    override fun onStart() {
        Log.d(TAG, "onStart: onStart")
        super.onStart()
    }

    override fun onRestart() {
        Log.d(TAG, "onRestart: onRestart")
        super.onRestart()
    }

    override fun onPause() {
        Log.d(TAG, "onPause: onPause")
        super.onPause()
    }

    override fun onStop() {

        Log.d(TAG, "onStop: onStop")
        super.onStop()
    }

    override fun onResume() {
        hideSystemUI()
        Log.d(TAG, "onResume: onResume")
        super.onResume()
    }

}