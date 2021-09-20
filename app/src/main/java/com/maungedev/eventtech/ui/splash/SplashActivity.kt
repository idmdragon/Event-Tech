package com.maungedev.eventtech.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.maungedev.eventtech.R
import com.maungedev.eventtech.ui.intro.IntroActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(mainLooper).postDelayed({
                startActivity(Intent(this, IntroActivity::class.java)).also{
                    finishAffinity()
                }
        }, 1000)
    }
}