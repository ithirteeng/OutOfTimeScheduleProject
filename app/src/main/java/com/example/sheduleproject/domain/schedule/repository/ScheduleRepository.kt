package com.example.sheduleproject.domain.schedule.repository

import com.example.sheduleproject.domain.common.entity.TimeSlotEntity
import com.example.sheduleproject.domain.schedule.entity.ClassEntity

interface ScheduleRepository {

    fun getTimeSlotListFromStorage(): List<TimeSlotEntity>

    fun saveClassesListToLocalStorage(classesList: List<ClassEntity>)

    fun getClassesListByDate(date: String): List<ClassEntity>

    suspend fun getClassInfo(classId: String): Result<ClassEntity>

    suspend fun getClassesList(
        startDate: String?,
        endDate: String?,
        clusterNumber: String?,
        educatorId: String?,
        dayOfWeek: String?,
        classType: String?
    ): Result<List<ClassEntity>>

}