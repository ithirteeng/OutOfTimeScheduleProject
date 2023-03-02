package com.example.sheduleproject.data.splash.datasource

import com.example.sheduleproject.data.common.model.TimeSlotModel
import com.example.sheduleproject.data.splash.api.SplashApi

class SplashDatasourceImpl(
    private val splashApi: SplashApi
) : SplashDatasource {

    override suspend fun getTimeSlotsList(): List<TimeSlotModel> =
        splashApi.getTimeSlotsList()

}