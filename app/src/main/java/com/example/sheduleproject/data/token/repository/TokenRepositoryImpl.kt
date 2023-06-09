package com.example.sheduleproject.data.token.repository

import com.example.sheduleproject.data.token.datasource.LocalTokenDatasource
import com.example.sheduleproject.data.token.datasource.RemoteTokenDatasource
import com.example.sheduleproject.data.token.mapper.toEntity
import com.example.sheduleproject.data.token.mapper.toModel
import com.example.sheduleproject.domain.token.entity.TokenEntity
import com.example.sheduleproject.domain.token.repository.TokenRepository

class TokenRepositoryImpl(
    private val localTokenDatasource: LocalTokenDatasource,
    private val remoteTokenDatasource: RemoteTokenDatasource
) : TokenRepository {
    override fun getTokenFromLocalStorage(): TokenEntity? {
        return localTokenDatasource.getToken()?.toEntity()
    }

    override fun saveTokenToLocalStorage(tokenEntity: TokenEntity?) {
        localTokenDatasource.saveToken(tokenEntity?.toModel())
    }

    override suspend fun refreshToken(tokenEntity: TokenEntity): TokenEntity {
        return remoteTokenDatasource.refreshToken(tokenEntity.toModel()).toEntity()
    }
}