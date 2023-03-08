package com.example.sheduleproject.data.entrance.login.repository

import com.example.sheduleproject.data.common.mapper.toEntity
import com.example.sheduleproject.data.common.mapper.toModel
import com.example.sheduleproject.data.token.storage.TokenStorage
import com.example.sheduleproject.data.entrance.login.datasource.LoginDatasource
import com.example.sheduleproject.data.entrance.login.mapper.toModel
import com.example.sheduleproject.domain.common.entity.TokenEntity
import com.example.sheduleproject.domain.entrance.login.entity.LoginEntity
import com.example.sheduleproject.domain.entrance.login.repository.LoginRepository

class LoginRepositoryImpl(
    private val datasource: LoginDatasource,
    private val tokenStorage: TokenStorage
) : LoginRepository {
    override suspend fun postLoginData(loginEntity: LoginEntity): Result<TokenEntity> {
        return try {
            Result.success(datasource.postLoginData(loginEntity.toModel()).toEntity())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun saveTokenToLocalStorage(tokenEntity: TokenEntity) {
        tokenStorage.saveTokens(tokenEntity.toModel())
    }
}