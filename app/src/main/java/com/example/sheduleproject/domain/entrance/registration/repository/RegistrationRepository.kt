package com.example.sheduleproject.domain.entrance.registration.repository

import com.example.sheduleproject.domain.token.entity.TokenEntity
import com.example.sheduleproject.domain.entrance.registration.entity.RegistrationEntity

interface RegistrationRepository {
    suspend fun postRegistrationData(registrationEntity: RegistrationEntity): Result<TokenEntity>

    fun saveTokenToLocalStorage(tokenEntity: TokenEntity)
}