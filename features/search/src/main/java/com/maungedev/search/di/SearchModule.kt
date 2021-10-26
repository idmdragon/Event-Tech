package com.maungedev.search.di

import com.maungedev.domain.usecase.search.SearchInteractor
import com.maungedev.domain.usecase.search.SearchUseCase
import com.maungedev.search.ui.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    factory<SearchUseCase> {
        SearchInteractor(get())
    }
    viewModel {
        SearchViewModel(get())
    }
}