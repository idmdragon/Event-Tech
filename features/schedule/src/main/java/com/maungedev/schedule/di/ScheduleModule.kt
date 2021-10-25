package com.maungedev.schedule.di

import com.maungedev.domain.usecase.schedule.ScheduleInteractor
import com.maungedev.domain.usecase.schedule.ScheduleUseCase
import com.maungedev.schedule.ScheduleViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val scheduleModule = module {
    factory<ScheduleUseCase> {
        ScheduleInteractor(get())
    }
    viewModel {
        ScheduleViewModel(get())
    }
}

