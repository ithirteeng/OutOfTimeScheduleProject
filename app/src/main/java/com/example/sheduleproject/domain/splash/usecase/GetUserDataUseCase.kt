package com.example.sheduleproject.domain.splash.usecase

import com.example.sheduleproject.domain.splash.entity.UserEntity
import com.example.sheduleproject.domain.splash.repository.SplashRepository

class GetUserDataUseCase(
    private val repository: SplashRepository
) {
    suspend operator fun invoke(): Result<UserEntity> =
        repository.getUserData()
}