package com.example.sheduleproject.data.splash.api

import com.example.sheduleproject.data.common.model.TimeSlotModel
import com.example.sheduleproject.data.splash.model.UserModel
import retrofit2.http.GET

interface SplashApi {
    @GET("api/class/slots")
    suspend fun getTimeSlotsList(): List<TimeSlotModel>

    @GET("api/users/self")
    suspend fun getUserData(): UserModel
}