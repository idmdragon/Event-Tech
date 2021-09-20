package com.maungedev.eventtech.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maungedev.eventtech.R

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        setFragment()
    }

    private fun setFragment() {
        val mFragmentManager = supportFragmentManager
        val mRegisterFragment = RegisterFragment()
        val fragment = mFragmentManager.findFragmentByTag(RegisterFragment::class.java.simpleName)

        if (fragment !is RegisterFragment) {
            mFragmentManager
                .beginTransaction()
                .add(R.id.frame_container, mRegisterFragment, RegisterFragment::class.java.simpleName)
                .commit()
        }
    }
}