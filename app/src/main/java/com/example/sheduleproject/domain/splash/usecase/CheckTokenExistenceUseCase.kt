package com.example.sheduleproject.domain.splash.usecase

import com.example.sheduleproject.domain.splash.repository.SplashRepository

class CheckTokenExistenceUseCase(
    private val repository: SplashRepository
) {
    operator fun invoke(): Boolean =
        repository.checkTokenExistence()
}