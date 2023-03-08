package com.example.sheduleproject.data.splash.api

import com.example.sheduleproject.data.common.mapper.model.TimeSlotModel
import retrofit2.http.GET

interface SplashApi {
    @GET("api/class/slots")
    suspend fun getTimeSlotsList(): List<TimeSlotModel>
}