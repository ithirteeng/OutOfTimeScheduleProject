package com.example.sheduleproject.data.entrance.login.repository

import com.example.sheduleproject.data.entrance.login.datasource.LocalLoginDatasource
import com.example.sheduleproject.data.entrance.login.datasource.RemoteLoginDatasource
import com.example.sheduleproject.data.entrance.login.mapper.toEntity
import com.example.sheduleproject.data.entrance.login.mapper.toModel
import com.example.sheduleproject.data.token.mapper.toEntity
import com.example.sheduleproject.data.token.mapper.toModel
import com.example.sheduleproject.domain.entrance.login.entity.LoginEntity
import com.example.sheduleproject.domain.entrance.login.entity.UserEntity
import com.example.sheduleproject.domain.entrance.login.repository.LoginRepository
import com.example.sheduleproject.domain.token.entity.TokenEntity

class LoginRepositoryImpl(
    private val remoteDatasource: RemoteLoginDatasource,
    private val localDatasource: LocalLoginDatasource
) : LoginRepository {
    override suspend fun postLoginData(loginEntity: LoginEntity): Result<TokenEntity> {
        return try {
            Result.success(remoteDatasource.postLoginData(loginEntity.toModel()).toEntity())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserData(): Result<UserEntity> {
        return try {
            Result.success(remoteDatasource.getUserData().toEntity())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun saveTokenToLocalStorage(tokenEntity: TokenEntity) {
        localDatasource.saveTokenToLocalStorage(tokenEntity.toModel())
    }

    override fun setIfUserWasAuthorizedFlag(authorizationFlag: Boolean) {
        localDatasource.setIfUserWasAuthorizedFlag(authorizationFlag)
    }
}