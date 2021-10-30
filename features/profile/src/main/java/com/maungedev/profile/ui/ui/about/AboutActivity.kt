package com.maungedev.profile.ui.ui.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maungedev.profile.R
import com.maungedev.profile.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}