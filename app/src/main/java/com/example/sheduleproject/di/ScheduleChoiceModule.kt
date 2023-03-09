package com.example.sheduleproject.di

import com.example.sheduleproject.data.schedulechoice.datasource.ScheduleChoiceDatasource
import com.example.sheduleproject.data.schedulechoice.datasource.ScheduleChoiceDatasourceImpl
import com.example.sheduleproject.data.schedulechoice.repository.ScheduleChoiceRepositoryImpl
import com.example.sheduleproject.domain.schedulechoice.repository.ScheduleChoiceRepository
import com.example.sheduleproject.domain.schedulechoice.usecase.GetClustersListUseCase
import com.example.sheduleproject.domain.schedulechoice.usecase.GetEducatorsListUseCase
import com.example.sheduleproject.domain.schedulechoice.usecase.GetLectureHallsListUseCase
import org.koin.dsl.module

val scheduleChoiceModule = module {
    factory<ScheduleChoiceDatasource> { ScheduleChoiceDatasourceImpl(api = get()) }
    factory<ScheduleChoiceRepository> { ScheduleChoiceRepositoryImpl(datasource = get()) }

    factory { GetLectureHallsListUseCase(repository = get()) }
    factory { GetEducatorsListUseCase(repository = get()) }
    factory { GetClustersListUseCase(repository = get()) }
}