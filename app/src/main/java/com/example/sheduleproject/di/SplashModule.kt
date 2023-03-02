package com.example.sheduleproject.di

import com.example.sheduleproject.data.common.storage.TimeSlotStorage
import com.example.sheduleproject.data.splash.datasource.SplashDatasource
import com.example.sheduleproject.data.splash.datasource.SplashDatasourceImpl
import com.example.sheduleproject.data.splash.repository.SplashRepositoryImpl
import com.example.sheduleproject.domain.splash.repository.SplashRepository
import com.example.sheduleproject.domain.splash.usecase.GetTimeslotsListUseCase
import com.example.sheduleproject.domain.splash.usecase.SaveTimeSlotsListUseCase
import com.example.sheduleproject.presentation.splash.SplashFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {

    factory<SplashDatasource> { SplashDatasourceImpl(splashApi = get()) }
    factory { TimeSlotStorage(context = get()) }

    factory<SplashRepository> {
        SplashRepositoryImpl(
            splashDatasource = get(),
            timeSlotStorage = get()
        )
    }

    factory { GetTimeslotsListUseCase(splashRepository = get()) }
    factory { SaveTimeSlotsListUseCase(splashRepository = get()) }

    viewModel {
        SplashFragmentViewModel(
            application = get(),
            getTimeslotsListUseCase = get(),
            saveTimeSlotsListUseCase = get()
        )
    }
}