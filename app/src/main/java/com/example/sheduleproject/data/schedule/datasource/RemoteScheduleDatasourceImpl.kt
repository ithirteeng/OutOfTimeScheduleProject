package com.example.sheduleproject.data.schedule.datasource

import com.example.sheduleproject.data.schedule.api.ScheduleApi
import com.example.sheduleproject.data.schedule.model.ClassModel

class RemoteScheduleDatasourceImpl(
    private val scheduleApi: ScheduleApi
) : RemoteScheduleDatasource {
    override suspend fun getClassesList(
        startDate: String?,
        endDate: String?,
        clusterNumber: String?,
        educatorId: String?,
        lectureHallId: String?,
        dayOfWeek: String?,
        classType: String?
    ): List<ClassModel> =
        scheduleApi.getClassesList(
            startDate = startDate,
            endDate = endDate,
            clusterNumber = clusterNumber,
            educatorId = educatorId,
            lectureHallId = lectureHallId,
            dayOfWeek = dayOfWeek,
            classType = classType
        )

    override suspend fun logout() =
        scheduleApi.logout()

}