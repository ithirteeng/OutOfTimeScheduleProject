package com.example.sheduleproject.domain.token.usecase

import com.example.sheduleproject.domain.token.entity.TokenEntity
import com.example.sheduleproject.domain.token.repository.TokenRepository

class RefreshTokenUseCase(
    private val repository: TokenRepository
) {

    suspend operator fun invoke(tokenEntity: TokenEntity): TokenEntity =
        repository.refreshToken(tokenEntity)
}