package com.example.sheduleproject.data.splash.repository

import com.example.sheduleproject.data.splash.datasource.LocalSplashDatasource
import com.example.sheduleproject.data.splash.datasource.RemoteSplashDatasource
import com.example.sheduleproject.data.splash.mapper.toEntitiesList
import com.example.sheduleproject.data.splash.mapper.toEntity
import com.example.sheduleproject.data.splash.mapper.toModelsList
import com.example.sheduleproject.domain.common.entity.TimeSlotEntity
import com.example.sheduleproject.domain.splash.entity.UserEntity
import com.example.sheduleproject.domain.splash.repository.SplashRepository

class SplashRepositoryImpl(
    private val remoteDatasource: RemoteSplashDatasource,
    private val localDatasource: LocalSplashDatasource
) : SplashRepository {
    override suspend fun getTimeSlotsList(): Result<List<TimeSlotEntity>?> {
        return try {
            Result.success(remoteDatasource.getTimeSlotsList().toEntitiesList())
        } catch (exception: java.lang.Exception) {
            Result.failure(exception)
        }

    }

    override suspend fun getUserData(): Result<UserEntity> {
        return try {
            Result.success(remoteDatasource.getUserData().toEntity())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun saveTimeSlotsListToLocalStorage(timeSlotsList: List<TimeSlotEntity>) {
        localDatasource.saveTimeSlotsListToLocalStorage(timeSlotsList.toModelsList())
    }

    override fun checkTokenExistence(): Boolean {
        return localDatasource.checkTokenExistence()
    }

    override fun checkIfUserWasAuthorized(): Boolean {
        return localDatasource.checkIfUserWasAuthorized()
    }
}