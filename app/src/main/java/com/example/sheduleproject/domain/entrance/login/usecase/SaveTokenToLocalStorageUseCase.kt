package com.example.sheduleproject.domain.entrance.login.usecase

import com.example.sheduleproject.domain.entrance.login.repository.LoginRepository
import com.example.sheduleproject.domain.token.entity.TokenEntity

class SaveTokenToLocalStorageUseCase(
    private val repository: LoginRepository
) {
    operator fun invoke(tokenEntity: TokenEntity) =
        repository.saveTokenToLocalStorage(tokenEntity)
}