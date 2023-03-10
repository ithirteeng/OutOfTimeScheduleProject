package com.example.sheduleproject.domain.token.repository

import com.example.sheduleproject.domain.token.entity.TokenEntity

interface TokenRepository {

    fun getTokenFromLocalStorage(): TokenEntity?

    fun saveTokenToLocalStorage(tokenEntity: TokenEntity?)

    suspend fun refreshToken(tokenEntity: TokenEntity): TokenEntity

}