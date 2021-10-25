package com.maungedev.profile.ui.di

import com.maungedev.domain.usecase.profile.ProfileInteractor
import com.maungedev.domain.usecase.profile.ProfileUseCase
import com.maungedev.profile.ui.ui.profile.ProfileViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileModule = module {
    factory<ProfileUseCase> {
        ProfileInteractor(get())
    }
    viewModel {
        ProfileViewModel(get())
    }
}

