package com.example.sheduleproject.domain.token.repository

import com.example.sheduleproject.domain.token.entity.TokenEntity

interface TokenRepository {
    suspend fun refreshToken(tokenEntity: TokenEntity): TokenEntity

    fun saveToken(tokenEntity: TokenEntity)

    fun getToken(): TokenEntity
}