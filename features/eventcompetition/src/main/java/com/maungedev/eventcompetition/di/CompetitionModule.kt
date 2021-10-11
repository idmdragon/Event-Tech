package com.maungedev.eventcompetition.di

import com.maungedev.domain.usecase.CompetitionInteractor
import com.maungedev.domain.usecase.CompetitionUseCase
import com.maungedev.eventcompetition.ui.CompetitionViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val competitionModule = module {
    factory<CompetitionUseCase> {
        CompetitionInteractor(get())
    }
    viewModel {
        CompetitionViewModel(get())
    }
}