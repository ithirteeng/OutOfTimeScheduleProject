package com.example.sheduleproject.data.schedule.datasource

import com.example.sheduleproject.data.common.model.TimeSlotModel
import com.example.sheduleproject.data.common.storage.TimeSlotStorage
import com.example.sheduleproject.data.common.storage.UserStorage
import com.example.sheduleproject.data.schedule.model.ClassModel
import com.example.sheduleproject.data.schedule.storage.ClassesStorage
import com.example.sheduleproject.data.token.storage.TokenStorage

class LocalScheduleDatasourceImpl(
    private val tokenStorage: TokenStorage,
    private val userStorage: UserStorage,
    private val timeSlotStorage: TimeSlotStorage,
    private val classesStorage: ClassesStorage
) : LocalScheduleDatasource {
    override fun checkTokenExistence(): Boolean =
        tokenStorage.checkUserTokenExistence()

    override fun removeToken() =
        tokenStorage.clearTokens()

    override fun setUserAuthorizationFlag(authorizationFlag: Boolean) =
        userStorage.setUserAuthorizationFlag(authorizationFlag)

    override fun getTimeSlotListFromStorage(): List<TimeSlotModel> {
        return timeSlotStorage.getTimeSlotListFromLocalStorage()
    }

    override fun saveClassesListToLocalStorage(classesList: List<ClassModel>) {
        classesStorage.saveClassesListToLocalStorage(classesList)
    }

    override fun getClassesListByDate(date: String): List<ClassModel> {
        return classesStorage.getClassesListByDate(date)
    }
}