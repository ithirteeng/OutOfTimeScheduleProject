package com.example.sheduleproject.domain.splash.repository

import com.example.sheduleproject.domain.common.entity.TimeSlotEntity

interface SplashRepository {

    suspend fun getTimeSlotsList(): Result<List<TimeSlotEntity>?>

    fun saveTimeSlotsListToLocalStorage(timeSlotsList: List<TimeSlotEntity>)
}