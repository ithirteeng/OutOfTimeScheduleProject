package com.example.sheduleproject.domain.entrance.login.usecase

import com.example.sheduleproject.domain.entrance.login.entity.UserEntity
import com.example.sheduleproject.domain.entrance.login.repository.LoginRepository

class GetUserDataUseCase(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(): Result<UserEntity> =
        repository.getUserData()
}