package com.example.sheduleproject.data.schedule.datasource

import com.example.sheduleproject.data.common.model.TimeSlotModel
import com.example.sheduleproject.data.schedule.model.ClassModel

interface LocalScheduleDatasource {

    fun checkTokenExistence(): Boolean

    fun removeToken()

    fun setUserAuthorizationFlag(authorizationFlag: Boolean)

    fun getTimeSlotListFromStorage(): List<TimeSlotModel>

    fun saveClassesListToLocalStorage(classesList: List<ClassModel>)

    fun getClassesListByDate(date: String): List<ClassModel>
}