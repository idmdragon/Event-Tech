package com.maungedev.eventtech.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.maungedev.eventtech.R
import com.maungedev.eventtech.constant.PageNameConstant.AUTHENTICATION_PAGE
import com.maungedev.eventtech.di.splashModule
import com.maungedev.eventtech.ui.intro.IntroActivity
import com.maungedev.eventtech.ui.main.MainActivity
import org.koin.core.context.loadKoinModules
import org.koin.android.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    val viewModel : SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        loadKoinModules(splashModule)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            observeHaveRunAppBefore()
        }, 1000L)

    }

    private fun observeUID() {
            if (isUserAlreadyHere()) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java)).also {
                    finishAffinity()
                }
            } else {
                startActivity(Intent(this@SplashActivity, Class.forName(AUTHENTICATION_PAGE))).also {
                    finishAffinity()
                }
            }
        }

    private fun observeHaveRunAppBefore() {
        viewModel.readPrefHaveRunAppBefore().observe(this, { haveRun ->
            if (haveRun) {
                observeUID()
            } else {
                startActivity(Intent(this@SplashActivity, IntroActivity::class.java)).also {
                    finishAffinity()
                }
            }
        })
    }

    private fun isUserAlreadyHere():Boolean {
        val auth = Firebase.auth
        val uid = auth.currentUser?.uid
        if(uid!=null){
            return true
        }
        return false
    }
}