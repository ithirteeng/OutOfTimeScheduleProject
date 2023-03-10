package com.example.sheduleproject.domain.token.usecase

import com.example.sheduleproject.domain.token.entity.TokenEntity
import com.example.sheduleproject.domain.token.repository.TokenRepository

class SaveTokenToLocalStorageUseCase(
    private val repository: TokenRepository
) {
    operator fun invoke(tokenEntity: TokenEntity?) =
        repository.saveTokenToLocalStorage(tokenEntity)
}