package com.example.sheduleproject.data.splash.datasource

import com.example.sheduleproject.data.common.model.TimeSlotModel
import com.example.sheduleproject.data.common.storage.TimeSlotStorage
import com.example.sheduleproject.data.common.storage.UserStorage
import com.example.sheduleproject.data.token.storage.TokenStorage

class LocalSplashDatasourceImpl(
    private val userStorage: UserStorage,
    private val tokenStorage: TokenStorage,
    private val timeSlotStorage: TimeSlotStorage
) : LocalSplashDatasource {
    override fun checkTokenExistence(): Boolean =
        tokenStorage.checkUserTokenExistence()

    override fun checkIfUserWasAuthorized(): Boolean =
        userStorage.checkIfUserWasAuthorized()

    override fun saveTimeSlotsListToLocalStorage(timeSlotsList: List<TimeSlotModel>) =
        timeSlotStorage.saveTimeSlotsListToLocalStorage(timeSlotsList)
}