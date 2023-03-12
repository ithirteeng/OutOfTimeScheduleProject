package com.example.sheduleproject.data.schedule.datasource

import com.example.sheduleproject.data.schedule.model.ClassModel

interface RemoteScheduleDatasource {
    suspend fun getClassesList(
        startDate: String?,
        endDate: String?,
        clusterNumber: String?,
        educatorId: String?,
        lectureHallId: String?,
        dayOfWeek: String?,
        classType: String?
    ): List<ClassModel>

    suspend fun logout()
}