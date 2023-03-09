package com.example.sheduleproject.domain.entrance.registration.usecase.second

import com.example.sheduleproject.domain.common.entity.TokenEntity
import com.example.sheduleproject.domain.entrance.registration.repository.RegistrationRepository

class SaveTokenToLocalStorageUseCase(
    private val repository: RegistrationRepository
) {
    operator fun invoke(tokenEntity: TokenEntity) =
        repository.saveTokenToLocalStorage(tokenEntity)
}