package com.example.sheduleproject.data.splash.repository

import com.example.sheduleproject.data.common.storage.TimeSlotStorage
import com.example.sheduleproject.data.splash.datasource.SplashDatasource
import com.example.sheduleproject.data.splash.mapper.toEntitiesList
import com.example.sheduleproject.data.splash.mapper.toModelsList
import com.example.sheduleproject.domain.common.entity.TimeSlotEntity
import com.example.sheduleproject.domain.splash.repository.SplashRepository

class SplashRepositoryImpl(
    private val splashDatasource: SplashDatasource,
    private val timeSlotStorage: TimeSlotStorage
) : SplashRepository {
    override suspend fun getTimeSlotsList(): Result<List<TimeSlotEntity>?> {
        return try {
            Result.success(splashDatasource.getTimeSlotsList().toEntitiesList())
        } catch (exception: java.lang.Exception) {
            Result.failure(exception)
        }

    }


    override fun saveTimeSlotsListToLocalStorage(timeSlotsList: List<TimeSlotEntity>) {
        timeSlotStorage.saveTimeSlotsListToLocalStorage(timeSlotsList.toModelsList())
    }
}