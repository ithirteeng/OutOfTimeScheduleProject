package com.example.sheduleproject.data.schedule.datasource

import com.example.sheduleproject.data.schedule.api.ScheduleApi
import com.example.sheduleproject.data.schedule.model.ClassModel

class ScheduleDatasourceImpl(
    private val scheduleApi: ScheduleApi
) : ScheduleDatasource {
    override suspend fun getClassesList(
        startDate: String?,
        endDate: String?,
        clusterNumber: String?,
        educatorId: String?,
        dayOfWeek: String?,
        classType: String?
    ): List<ClassModel> =
        scheduleApi.getClassesList(
            startDate = startDate,
            endDate = endDate,
            clusterNumber = clusterNumber,
            educatorId = educatorId,
            dayOfWeek = dayOfWeek,
            classType = classType
        )


    override suspend fun getClassInfo(classId: String): ClassModel =
        scheduleApi.getClassInfo(classId = classId)

}