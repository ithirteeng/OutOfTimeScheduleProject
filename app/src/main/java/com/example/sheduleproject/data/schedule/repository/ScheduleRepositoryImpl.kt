package com.example.sheduleproject.data.schedule.repository

import com.example.sheduleproject.data.schedule.datasource.LocalScheduleDatasource
import com.example.sheduleproject.data.schedule.datasource.RemoteScheduleDatasource
import com.example.sheduleproject.data.schedule.mapper.toEntitiesList
import com.example.sheduleproject.data.schedule.mapper.toEntity
import com.example.sheduleproject.data.schedule.mapper.toModelsList
import com.example.sheduleproject.domain.common.entity.TimeSlotEntity
import com.example.sheduleproject.domain.schedule.entity.ClassEntity
import com.example.sheduleproject.domain.schedule.repository.ScheduleRepository

class ScheduleRepositoryImpl(
    private val localDatasource: LocalScheduleDatasource,
    private val remoteDatasource: RemoteScheduleDatasource
) : ScheduleRepository {
    override fun checkTokenExistence(): Boolean =
        localDatasource.checkTokenExistence()

    override fun removeToken() =
        localDatasource.removeToken()

    override fun setUserAuthorizationFlag(authorizationFlag: Boolean) =
        localDatasource.setUserAuthorizationFlag(authorizationFlag)

    override fun getTimeSlotListFromStorage(): List<TimeSlotEntity> {
        return localDatasource.getTimeSlotListFromStorage().toEntitiesList()
    }

    override fun saveClassesListToLocalStorage(classesList: List<ClassEntity>) {
        localDatasource.saveClassesListToLocalStorage(classesList.toModelsList())
    }

    override fun getClassesListByDate(date: String): List<ClassEntity> {
        return localDatasource.getClassesListByDate(date).toEntitiesList()
    }

    override suspend fun getClassesList(
        startDate: String?,
        endDate: String?,
        clusterNumber: String?,
        educatorId: String?,
        lectureHallId: String?,
        dayOfWeek: String?,
        classType: String?
    ): Result<List<ClassEntity>> {
        return try {
            Result.success(
                remoteDatasource.getClassesList(
                    startDate = startDate,
                    endDate = endDate,
                    clusterNumber = clusterNumber,
                    educatorId = educatorId,
                    lectureHallId = lectureHallId,
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
            Result.success(remoteDatasource.getClassInfo(classId = classId).toEntity())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout(): Result<Boolean> {
        return try {
            remoteDatasource.logout()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}