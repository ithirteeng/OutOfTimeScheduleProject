package com.example.sheduleproject.data.entrance.registration.repository

import com.example.sheduleproject.data.token.mapper.toEntity
import com.example.sheduleproject.data.token.mapper.toModel
import com.example.sheduleproject.data.entrance.registration.datasource.RegistrationDatasource
import com.example.sheduleproject.data.entrance.registration.mapper.toModel
import com.example.sheduleproject.data.token.storage.TokenStorage
import com.example.sheduleproject.domain.token.entity.TokenEntity
import com.example.sheduleproject.domain.entrance.registration.entity.RegistrationEntity
import com.example.sheduleproject.domain.entrance.registration.repository.RegistrationRepository

class RegistrationRepositoryImpl(
    private val datasource: RegistrationDatasource,
    private val tokenStorage: TokenStorage
) : RegistrationRepository {
    override suspend fun postRegistrationData(registrationEntity: RegistrationEntity): Result<TokenEntity> {
        return try {
            Result.success(datasource.postRegistrationData(registrationEntity.toModel()).toEntity())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun saveTokenToLocalStorage(tokenEntity: TokenEntity) {
        tokenStorage.saveTokens(tokenEntity.toModel())
    }
}