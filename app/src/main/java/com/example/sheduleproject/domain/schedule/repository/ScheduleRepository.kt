package com.example.sheduleproject.domain.schedule.repository

import com.example.sheduleproject.domain.common.entity.TimeSlotEntity
import com.example.sheduleproject.domain.schedule.entity.ClassEntity

interface ScheduleRepository {

    fun checkTokenExistence(): Boolean

    fun removeToken()

    fun setUserAuthorizationFlag(authorizationFlag: Boolean)

    fun getTimeSlotListFromStorage(): List<TimeSlotEntity>

    fun saveClassesListToLocalStorage(classesList: List<ClassEntity>)

    fun getClassesListByDate(date: String): List<ClassEntity>

    suspend fun logout(): Result<Boolean>

    suspend fun getClassesList(
        startDate: String?,
        endDate: String?,
        clusterNumber: String?,
        educatorId: String?,
        lectureHallId: String?,
        dayOfWeek: String?,
        classType: String?
    ): Result<List<ClassEntity>>

}