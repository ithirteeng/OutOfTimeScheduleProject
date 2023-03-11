package com.example.sheduleproject.data.splash.datasource

import com.example.sheduleproject.data.common.model.TimeSlotModel
import com.example.sheduleproject.data.splash.model.UserModel

interface RemoteSplashDatasource {
    suspend fun getTimeSlotsList(): List<TimeSlotModel>

    suspend fun getUserData(): UserModel
}