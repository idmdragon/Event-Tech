package com.maungedev.eventtech.ui.intro

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.maungedev.eventtech.R
import com.maungedev.eventtech.databinding.ActivityIntroBinding
import com.maungedev.eventtech.ui.main.MainActivity

class IntroActivity : AppCompatActivity() {


    private lateinit var binding : ActivityIntroBinding

    private val introSlideAdapter = IntroSlideAdapter(
        listOf(
            IntroSlide(
                "Temukan Event IT",
                "Temukan event yang sesuai dengan minat kamu",
                R.drawable.ic_ilus_intro_1
            ),
            IntroSlide(
                "Pengingat Event",
                "Kami sediakan pengingat event agar kamu tidak kelupaan",
                R.drawable.ic_ilus_intro_2
            ),
            IntroSlide(
                "Tambahkan Favorit",
                "Kamu bisa menyimpan detail event dengan favorit",
                R.drawable.ic_ilus_intro_3
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.introSliderViewPager.adapter = introSlideAdapter
        setupIndicator()
        setCurrentIndicator(0)
        binding.introSliderViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })

        binding.btnNext.setOnClickListener{
            if(binding.introSliderViewPager.currentItem + 1 < introSlideAdapter.itemCount)
            {
                binding.introSliderViewPager.currentItem+=1
            }else{

                Intent (applicationContext, MainActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
        binding.tvSkip.setOnClickListener {
            Intent (applicationContext, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }

    private fun setupIndicator(){
        val indicators = arrayOfNulls<ImageView>(introSlideAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply{
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_incative
                    )
                )
                this?.layoutParams = layoutParams
            }
            binding.indicatorContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index : Int){
        val childCount = binding.indicatorContainer.childCount
        for (i in 0 until childCount){
            val imageView = binding.indicatorContainer[i] as ImageView
            if(i == index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            }
            else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_incative
                    ))
            }
        }
    }
}