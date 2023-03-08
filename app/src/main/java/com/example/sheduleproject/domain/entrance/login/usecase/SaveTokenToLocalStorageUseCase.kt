package com.example.sheduleproject.domain.entrance.login.usecase

import com.example.sheduleproject.domain.common.entity.TokenEntity
import com.example.sheduleproject.domain.entrance.login.repository.LoginRepository

class SaveTokenToLocalStorageUseCase(
    private val repository: LoginRepository
) {
    operator fun invoke(tokenEntity: TokenEntity) =
        repository.saveTokenToLocalStorage(tokenEntity)
}