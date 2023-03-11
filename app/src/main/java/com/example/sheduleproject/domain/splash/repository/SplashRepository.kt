package com.example.sheduleproject.domain.splash.repository

import com.example.sheduleproject.domain.common.entity.TimeSlotEntity
import com.example.sheduleproject.domain.splash.entity.UserEntity

interface SplashRepository {

    suspend fun getTimeSlotsList(): Result<List<TimeSlotEntity>?>

    suspend fun getUserData(): Result<UserEntity>

    fun saveTimeSlotsListToLocalStorage(timeSlotsList: List<TimeSlotEntity>)

    fun checkTokenExistence(): Boolean

    fun checkIfUserWasAuthorized(): Boolean
}