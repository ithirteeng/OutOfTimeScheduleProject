package com.example.sheduleproject.data.splash.datasource

import com.example.sheduleproject.data.common.mapper.model.TimeSlotModel

interface SplashDatasource {
    suspend fun getTimeSlotsList(): List<TimeSlotModel>
}