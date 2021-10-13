package com.maungedev.detail.ui.webview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.maungedev.detail.databinding.ActivityRegistrationBinding
import com.maungedev.eventtech.constant.ExtraNameConstant.EVENT_LINK_REGISTRATION

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val linkRegistration= intent.getStringExtra(EVENT_LINK_REGISTRATION) as String
        binding.apply {
            wvRegistration.loadUrl(linkRegistration)
            val webSetting: WebSettings = wvRegistration.settings
            webSetting.javaScriptEnabled = true
            wvRegistration.webViewClient  = WebViewClient()

            btnBack.setOnClickListener{
                onBackPressed()
            }
        }


    }
}