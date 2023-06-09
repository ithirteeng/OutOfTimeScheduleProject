package com.example.sheduleproject.di

import com.example.sheduleproject.data.schedule.datasource.LocalScheduleDatasource
import com.example.sheduleproject.data.schedule.datasource.LocalScheduleDatasourceImpl
import com.example.sheduleproject.data.schedule.datasource.RemoteScheduleDatasource
import com.example.sheduleproject.data.schedule.datasource.RemoteScheduleDatasourceImpl
import com.example.sheduleproject.data.schedule.repository.ScheduleRepositoryImpl
import com.example.sheduleproject.data.schedule.storage.ClassesStorage
import com.example.sheduleproject.domain.schedule.repository.ScheduleRepository
import com.example.sheduleproject.domain.schedule.usecase.*
import com.example.sheduleproject.presentation.schedule.ScheduleFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val scheduleModule = module {
    factory<RemoteScheduleDatasource> { RemoteScheduleDatasourceImpl(scheduleApi = get()) }
    factory<LocalScheduleDatasource> {
        LocalScheduleDatasourceImpl(
            timeSlotStorage = get(),
            classesStorage = get(),
            userStorage = get(),
            tokenStorage = get()
        )
    }
    single { ClassesStorage(context = get()) }

    factory<ScheduleRepository> {
        ScheduleRepositoryImpl(
            localDatasource = get(),
            remoteDatasource = get()
        )
    }
    factory { GetTimeSlotListUseCase(repository = get()) }
    factory { GetClassesListUseCase(repository = get()) }
    factory { GetClassesListByDateFromStorageUseCase(repository = get()) }
    factory { SaveClassesListToLocalStorageUseCase(repository = get()) }
    factory { SetUserAuthorizationFlagUseCase(repository = get()) }
    factory { CheckTokenExistenceUseCase(repository = get()) }
    factory { RemoveTokenUseCase(repository = get()) }
    factory { LogoutUseCase(repository = get()) }

    viewModel {
        ScheduleFragmentViewModel(
            application = get(),
            getTimeSlotListUseCase = get(),
            getClassesListUseCase = get(),
            getClassesListByDateFromStorageUseCase = get(),
            saveClassesListToLocalStorageUseCase = get(),
            setUserAuthorizationFlagUseCase = get(),
            checkTokenExistenceUseCase = get(),
            removeTokenUseCase = get(),
            logoutUseCase = get()
        )
    }
}