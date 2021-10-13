package com.maungedev.detail.di

import com.maungedev.detail.ui.DetailViewModel
import com.maungedev.domain.usecase.ConferenceInteractor
import com.maungedev.domain.usecase.ConferenceUseCase
import com.maungedev.domain.usecase.DetailInteractor
import com.maungedev.domain.usecase.DetailUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {
    factory<DetailUseCase> {
        DetailInteractor(get())
    }
    viewModel {
        DetailViewModel(get())
    }
}