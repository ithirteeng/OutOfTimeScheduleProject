package com.example.sheduleproject.di

import com.example.sheduleproject.data.common.storage.TimeSlotStorage
import com.example.sheduleproject.data.schedule.datasource.ScheduleDatasource
import com.example.sheduleproject.data.schedule.datasource.ScheduleDatasourceImpl
import com.example.sheduleproject.data.schedule.repository.ScheduleRepositoryImpl
import com.example.sheduleproject.data.schedule.storage.ClassesStorage
import com.example.sheduleproject.domain.schedule.repository.ScheduleRepository
import com.example.sheduleproject.domain.schedule.usecase.*
import com.example.sheduleproject.presentation.schedule.ScheduleFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val scheduleModule = module {
    factory<ScheduleDatasource> { ScheduleDatasourceImpl(scheduleApi = get()) }

    factory { TimeSlotStorage(context = get()) }
    factory { ClassesStorage(context = get()) }

    factory<ScheduleRepository> {
        ScheduleRepositoryImpl(
            timeSlotStorage = get(),
            scheduleDatasource = get(),
            classesStorage = get()
        )
    }
    factory { GetTimeSlotListUseCase(repository = get()) }
    factory { GetClassesListUseCase(repository = get()) }
    factory { GetClassInfoUseCase(repository = get()) }
    factory { GetClassesListByDateFromStorageUseCase(repository = get()) }
    factory { SaveClassesListToLocalStorageUseCase(repository = get()) }

    viewModel {
        ScheduleFragmentViewModel(
            application = get(),
            getTimeSlotListUseCase = get(),
            getClassesListUseCase = get(),
            getClassInfoUseCase = get(),
            getClassesListByDateFromStorageUseCase = get(),
            saveClassesListToLocalStorageUseCase = get()
        )
    }
}