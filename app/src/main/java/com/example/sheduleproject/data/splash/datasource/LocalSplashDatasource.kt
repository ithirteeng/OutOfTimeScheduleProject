package com.example.sheduleproject.data.splash.datasource

import com.example.sheduleproject.data.common.model.TimeSlotModel

interface LocalSplashDatasource {

    fun checkTokenExistence(): Boolean

    fun checkIfUserWasAuthorized(): Boolean

    fun saveTimeSlotsListToLocalStorage(timeSlotsList: List<TimeSlotModel>)
}