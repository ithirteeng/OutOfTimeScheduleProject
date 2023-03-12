package com.example.sheduleproject.domain.entrance.registration.repository

import com.example.sheduleproject.domain.entrance.registration.entity.RegistrationEntity
import com.example.sheduleproject.domain.token.entity.TokenEntity

interface RegistrationRepository {
    suspend fun postRegistrationData(registrationEntity: RegistrationEntity): Result<TokenEntity>

    fun saveTokenToLocalStorage(tokenEntity: TokenEntity)

    fun setIfUserWasAuthorizedFlag(authorizationFlag: Boolean)
}