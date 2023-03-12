package com.example.sheduleproject.domain.entrance.registration.usecase.second

import com.example.sheduleproject.domain.entrance.registration.repository.RegistrationRepository
import com.example.sheduleproject.domain.token.entity.TokenEntity

class SaveTokenToLocalStorageUseCase(
    private val repository: RegistrationRepository
) {
    operator fun invoke(tokenEntity: TokenEntity) =
        repository.saveTokenToLocalStorage(tokenEntity)
}