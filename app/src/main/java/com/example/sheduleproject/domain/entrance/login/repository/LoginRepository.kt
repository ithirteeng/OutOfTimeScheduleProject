package com.example.sheduleproject.domain.entrance.login.repository

import com.example.sheduleproject.domain.common.entity.TokenEntity
import com.example.sheduleproject.domain.entrance.login.entity.LoginEntity

interface LoginRepository {
    suspend fun postLoginData(loginEntity: LoginEntity): Result<TokenEntity>

    fun saveTokenToLocalStorage(tokenEntity: TokenEntity)
}