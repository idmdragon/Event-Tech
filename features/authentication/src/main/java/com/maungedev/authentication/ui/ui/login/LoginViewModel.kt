package com.maungedev.authentication.ui.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.maungedev.domain.usecase.auth.AuthUseCase

class LoginViewModel(private val authUseCase: AuthUseCase) : ViewModel() {
    fun signIn(email: String, password: String) =
        authUseCase.signInUser(email,password).asLiveData()

    fun resetPassword(email: String) =
        authUseCase.resetPassword(email).asLiveData()

}