package com.example.sheduleproject.domain.entrance.registration.usecase.second

import com.example.sheduleproject.domain.entrance.registration.entity.RegistrationEntity
import com.example.sheduleproject.domain.entrance.registration.repository.RegistrationRepository
import com.example.sheduleproject.domain.token.entity.TokenEntity

class PostRegistrationDataUseCase(
    private val repository: RegistrationRepository
) {
    suspend operator fun invoke(registrationEntity: RegistrationEntity): Result<TokenEntity> =
        repository.postRegistrationData(registrationEntity)
}