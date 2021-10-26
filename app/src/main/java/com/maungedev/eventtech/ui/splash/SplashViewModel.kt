package com.maungedev.eventtech.ui.splash

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.maungedev.eventtech.utils.DataStore
import kotlinx.coroutines.Dispatchers

class SplashViewModel(application: Application) : ViewModel() {
    private val dataStorePlayground = DataStore.getInstance(application)

    fun readPrefHaveRunAppBefore() = dataStorePlayground.readPrefHaveRunAppBefore().asLiveData(
        Dispatchers.IO
    )
}