package com.example.sheduleproject.domain.token.repository

import com.example.sheduleproject.data.common.model.TokenModel
import com.example.sheduleproject.domain.token.entity.TokenEntity
import retrofit2.Response

interface TokenRepository {
    fun refreshToken(tokenEntity: TokenEntity): Response<TokenModel>

    fun saveToken(tokenEntity: TokenEntity)

    fun getToken(): TokenEntity
}