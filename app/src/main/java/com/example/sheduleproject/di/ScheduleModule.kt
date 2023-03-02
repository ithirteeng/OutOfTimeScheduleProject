package com.example.sheduleproject.di

import com.example.sheduleproject.data.schedule.repository.ScheduleRepositoryImpl
import com.example.sheduleproject.data.common.storage.TimeSlotStorage
import com.example.sheduleproject.domain.schedule.repository.ScheduleRepository
import com.example.sheduleproject.domain.schedule.usecase.GetTimeSlotListUseCase
import com.example.sheduleproject.presentation.schedule.ScheduleFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val scheduleModule = module {
    factory { TimeSlotStorage(context = get()) }
    factory<ScheduleRepository> { ScheduleRepositoryImpl(timeSlotStorage = get()) }
    factory { GetTimeSlotListUseCase(repository = get()) }

    viewModel {
        ScheduleFragmentViewModel(
            application = get(),
            getTimeSlotListUseCase = get()
        )
    }
}