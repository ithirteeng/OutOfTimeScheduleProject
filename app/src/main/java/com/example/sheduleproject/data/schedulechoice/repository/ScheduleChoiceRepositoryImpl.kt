package com.example.sheduleproject.data.schedulechoice.repository

import com.example.sheduleproject.data.schedulechoice.datasource.ScheduleChoiceDatasource
import com.example.sheduleproject.data.schedulechoice.mapper.toEntitiesList
import com.example.sheduleproject.domain.schedulechoice.entity.ClusterEntity
import com.example.sheduleproject.domain.schedulechoice.entity.EducatorEntity
import com.example.sheduleproject.domain.schedulechoice.entity.LectureHallEntity
import com.example.sheduleproject.domain.schedulechoice.repository.ScheduleChoiceRepository

class ScheduleChoiceRepositoryImpl(
    private val datasource: ScheduleChoiceDatasource
) : ScheduleChoiceRepository {
    override suspend fun getClustersList(): Result<List<ClusterEntity>> {
        return try {
            Result.success(datasource.getClustersList().toEntitiesList())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getEducatorList(): Result<List<EducatorEntity>> {
        return try {
            Result.success(datasource.getEducatorList().toEntitiesList())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getLectureHallsList(): Result<List<LectureHallEntity>> {
        return try {
            Result.success(datasource.getLectureHallsList().toEntitiesList())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}