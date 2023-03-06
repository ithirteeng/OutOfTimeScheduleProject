package com.example.sheduleproject.data.schedule.datasource

import com.example.sheduleproject.data.schedule.model.ClassModel

interface ScheduleDatasource {
    suspend fun getClassesList(
        startDate: String?,
        endDate: String?,
        clusterNumber: String?,
        educatorId: String?,
        dayOfWeek: String?,
        classType: String?
    ): List<ClassModel>

    suspend fun getClassInfo(classId: String): ClassModel
}