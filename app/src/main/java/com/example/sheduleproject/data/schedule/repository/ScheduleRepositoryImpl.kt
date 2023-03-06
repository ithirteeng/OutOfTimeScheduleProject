package com.example.sheduleproject.data.schedule.repository

import com.example.sheduleproject.data.common.storage.TimeSlotStorage
import com.example.sheduleproject.data.schedule.datasource.ScheduleDatasource
import com.example.sheduleproject.data.schedule.mapper.toEntitiesList
import com.example.sheduleproject.data.schedule.mapper.toEntity
import com.example.sheduleproject.data.schedule.mapper.toModelsList
import com.example.sheduleproject.data.schedule.storage.ClassesStorage
import com.example.sheduleproject.domain.common.entity.TimeSlotEntity
import com.example.sheduleproject.domain.schedule.entity.ClassEntity
import com.example.sheduleproject.domain.schedule.repository.ScheduleRepository

class ScheduleRepositoryImpl(
    private val timeSlotStorage: TimeSlotStorage,
    private val classesStorage: ClassesStorage,
    private val scheduleDatasource: ScheduleDatasource
) : ScheduleRepository {
    override fun getTimeSlotListFromStorage(): List<TimeSlotEntity> {
        return timeSlotStorage.getTimeSLotListFromLocalStorage().toEntitiesList()
    }

    override fun saveClassesListToLocalStorage(classesList: List<ClassEntity>) {
        classesStorage.saveClassesListToLocalStorage(classesList.toModelsList())
    }

    override fun getClassesListByDate(date: String): List<ClassEntity> {
        return classesStorage.getClassesListByDate(date).toEntitiesList()
    }

    override suspend fun getClassesList(
        startDate: String?,
        endDate: String?,
        clusterNumber: String?,
        educatorId: String?,
        dayOfWeek: String?,
        classType: String?
    ): Result<List<ClassEntity>> {
        return try {
            Result.success(
                scheduleDatasource.getClassesList(
                    startDate = startDate,
                    endDate = endDate,
                    clusterNumber = clusterNumber,
                    educatorId = educatorId,
                    dayOfWeek = dayOfWeek,
                    classType = classType
                ).toEntitiesList()
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getClassInfo(classId: String): Result<ClassEntity> {
        return try {
            Result.success(scheduleDatasource.getClassInfo(classId = classId).toEntity())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}