package com.maungedev.authentication.ui.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.maungedev.domain.model.User
import com.maungedev.domain.usecase.AuthUseCase

class AuthViewModel(private val authUseCase: AuthUseCase) : ViewModel() {
    fun signUp(email: String, password: String, user: User) =
        authUseCase.signUpUser(email,password,user).asLiveData()
}