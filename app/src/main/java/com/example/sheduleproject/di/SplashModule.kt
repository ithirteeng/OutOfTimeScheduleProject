package com.example.sheduleproject.di

import com.example.sheduleproject.data.common.storage.TimeSlotStorage
import com.example.sheduleproject.data.splash.datasource.LocalSplashDatasource
import com.example.sheduleproject.data.splash.datasource.LocalSplashDatasourceImpl
import com.example.sheduleproject.data.splash.datasource.RemoteSplashDatasource
import com.example.sheduleproject.data.splash.datasource.RemoteSplashDatasourceImpl
import com.example.sheduleproject.data.splash.repository.SplashRepositoryImpl
import com.example.sheduleproject.domain.splash.repository.SplashRepository
import com.example.sheduleproject.domain.splash.usecase.*
import com.example.sheduleproject.presentation.splash.SplashFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {

    factory<RemoteSplashDatasource> { RemoteSplashDatasourceImpl(splashApi = get()) }
    factory<LocalSplashDatasource> {
        LocalSplashDatasourceImpl(
            userStorage = get(),
            tokenStorage = get(),
            timeSlotStorage = get()
        )
    }
    single { TimeSlotStorage(context = get()) }

    factory<SplashRepository> {
        SplashRepositoryImpl(
            remoteDatasource = get(),
            localDatasource = get()
        )
    }

    factory { GetTimeslotsListUseCase(repository = get()) }
    factory { SaveTimeSlotsListUseCase(repository = get()) }
    factory { CheckTokenExistenceUseCase(repository = get()) }
    factory { CheckIfUserWasAuthorizedUseCase(repository = get()) }
    factory { GetUserDataUseCase(repository = get()) }

    viewModel {
        SplashFragmentViewModel(
            application = get(),
            getTimeslotsListUseCase = get(),
            saveTimeSlotsListUseCase = get(),
            checkIfUserWasAuthorizedUseCase = get(),
            checkTokenExistenceUseCase = get(),
            getUserDataUseCase = get()
        )
    }
}