package com.maungedev.detail.di

import com.maungedev.detail.ui.DetailViewModel
import com.maungedev.domain.usecase.detail.DetailInteractor
import com.maungedev.domain.usecase.detail.DetailUseCase
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