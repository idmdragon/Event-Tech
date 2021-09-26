package com.maungedev.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maungedev.detail.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding){
            btnBack.setOnClickListener {
                onBackPressed()
            }
        }
    }
}