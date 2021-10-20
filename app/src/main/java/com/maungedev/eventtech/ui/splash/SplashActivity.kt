package com.maungedev.eventtech.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.maungedev.eventtech.R
import com.maungedev.eventtech.constant.PageNameConstant.AUTHENTICATION_PAGE
import com.maungedev.eventtech.ui.intro.IntroActivity
import com.maungedev.eventtech.ui.main.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(mainLooper).postDelayed({
            if(isUserAlreadyHere()){
                startActivity(Intent(this@SplashActivity, IntroActivity::class.java)).also {
                    finishAffinity()
                }
            }else{
                startActivity(Intent(this@SplashActivity, IntroActivity::class.java)).also {
                    finishAffinity()
                }
            }
        }, 1000)
    }

    private fun isUserAlreadyHere():Boolean {
        val auth = Firebase.auth
        if(auth.currentUser?.uid!=null){
            return true
        }
        return false
    }
}