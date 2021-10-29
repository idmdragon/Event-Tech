package com.maungedev.authentication.ui.ui.register

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.maungedev.domain.model.User
import com.maungedev.domain.usecase.auth.AuthUseCase
import com.maungedev.eventtech.utils.DataStore
import kotlinx.coroutines.launch

class RegisterViewModel(private val authUseCase: AuthUseCase,
                        application: Application) : ViewModel() {

    private val datastore = DataStore.getInstance(application)

    fun setNotFirstTimeOpenApp(isFirstTime: Boolean) = viewModelScope.launch {
        datastore.savePrefHaveRunAppBefore(isFirstTime)
    }


    fun signUp(email: String, password: String, user: User) =
        authUseCase.signUpUser(email,password,user).asLiveData()
}