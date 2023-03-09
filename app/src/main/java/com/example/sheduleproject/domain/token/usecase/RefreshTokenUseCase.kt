package com.example.sheduleproject.domain.token.usecase

import com.example.sheduleproject.data.common.model.TokenModel
import com.example.sheduleproject.domain.token.entity.TokenEntity
import com.example.sheduleproject.domain.token.repository.TokenRepository
import retrofit2.Response

class RefreshTokenUseCase(
    private val repository: TokenRepository
) {
    operator fun invoke(tokenEntity: TokenEntity): Response<TokenModel> =
        repository.refreshToken(tokenEntity)
}