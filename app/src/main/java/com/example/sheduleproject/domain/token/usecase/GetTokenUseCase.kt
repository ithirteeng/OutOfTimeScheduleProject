package com.example.sheduleproject.domain.token.usecase

import com.example.sheduleproject.domain.token.entity.TokenEntity
import com.example.sheduleproject.domain.token.repository.TokenRepository

class GetTokenUseCase(
    private val repository: TokenRepository
) {
    operator fun invoke(): TokenEntity =
        repository.getToken()
}