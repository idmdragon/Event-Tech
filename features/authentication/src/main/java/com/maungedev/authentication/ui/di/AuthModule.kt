package com.maungedev.authentication.ui.di

import com.maungedev.authentication.ui.ui.login.LoginViewModel
import com.maungedev.authentication.ui.ui.register.RegisterViewModel
import com.maungedev.domain.usecase.auth.AuthInteractor
import com.maungedev.domain.usecase.auth.AuthUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    factory<AuthUseCase> {
        AuthInteractor(get())
    }
    viewModel {
        RegisterViewModel(get(),androidApplication())

    }
    viewModel {
        LoginViewModel(get())
    }

}

