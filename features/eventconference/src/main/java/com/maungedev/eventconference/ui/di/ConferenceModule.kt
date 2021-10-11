package com.maungedev.eventconference.ui.di

import com.maungedev.domain.usecase.ConferenceInteractor
import com.maungedev.domain.usecase.ConferenceUseCase
import com.maungedev.eventconference.ui.ui.ConferenceViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val conferenceModule = module {
    factory<ConferenceUseCase> {
        ConferenceInteractor(get())
    }
    viewModel {
        ConferenceViewModel(get())
    }
}