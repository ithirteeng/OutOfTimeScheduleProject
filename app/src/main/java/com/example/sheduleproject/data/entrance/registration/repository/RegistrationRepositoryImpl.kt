package com.example.sheduleproject.data.entrance.registration.repository

import com.example.sheduleproject.data.entrance.registration.datasource.LocalRegistrationDatasource
import com.example.sheduleproject.data.entrance.registration.datasource.RemoteRegistrationDatasource
import com.example.sheduleproject.data.entrance.registration.mapper.toModel
import com.example.sheduleproject.data.token.mapper.toEntity
import com.example.sheduleproject.data.token.mapper.toModel
import com.example.sheduleproject.domain.entrance.registration.entity.RegistrationEntity
import com.example.sheduleproject.domain.entrance.registration.repository.RegistrationRepository
import com.example.sheduleproject.domain.token.entity.TokenEntity

class RegistrationRepositoryImpl(
    private val remoteDatasource: RemoteRegistrationDatasource,
    private val localDatasource: LocalRegistrationDatasource
) : RegistrationRepository {
    override suspend fun postRegistrationData(registrationEntity: RegistrationEntity): Result<TokenEntity> {
        return try {
            Result.success(
                remoteDatasource.postRegistrationData(registrationEntity.toModel()).toEntity()
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun saveTokenToLocalStorage(tokenEntity: TokenEntity) {
        localDatasource.saveTokenToLocalStorage(tokenEntity.toModel())
    }
}