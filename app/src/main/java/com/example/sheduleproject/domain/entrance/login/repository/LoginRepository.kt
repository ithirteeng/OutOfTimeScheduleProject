package com.example.sheduleproject.domain.entrance.login.repository

import com.example.sheduleproject.domain.entrance.login.entity.LoginEntity
import com.example.sheduleproject.domain.entrance.login.entity.UserEntity
import com.example.sheduleproject.domain.token.entity.TokenEntity

interface LoginRepository {
    suspend fun postLoginData(loginEntity: LoginEntity): Result<TokenEntity>

    suspend fun getUserData(): Result<UserEntity>

    fun saveTokenToLocalStorage(tokenEntity: TokenEntity)
}