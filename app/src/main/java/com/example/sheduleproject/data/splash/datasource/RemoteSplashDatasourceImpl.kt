package com.example.sheduleproject.data.splash.datasource

import com.example.sheduleproject.data.common.model.TimeSlotModel
import com.example.sheduleproject.data.splash.api.SplashApi
import com.example.sheduleproject.data.splash.model.UserModel

class RemoteSplashDatasourceImpl(
    private val splashApi: SplashApi
) : RemoteSplashDatasource {

    override suspend fun getTimeSlotsList(): List<TimeSlotModel> =
        splashApi.getTimeSlotsList()

    override suspend fun getUserData(): UserModel =
        splashApi.getUserData()

}