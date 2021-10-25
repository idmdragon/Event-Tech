package com.maungedev.favorite.di

import com.maungedev.domain.usecase.favorite.FavoriteInteractor
import com.maungedev.domain.usecase.favorite.FavoriteUseCase
import com.maungedev.favorite.ui.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    factory<FavoriteUseCase> {
        FavoriteInteractor(get())
    }
    viewModel {
        FavoriteViewModel(get())
    }
}

