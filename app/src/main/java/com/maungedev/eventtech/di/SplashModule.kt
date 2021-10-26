package com.maungedev.eventtech.di

import com.maungedev.eventtech.ui.splash.SplashViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {
    viewModel { SplashViewModel(androidApplication()) }
}
