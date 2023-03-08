package com.example.sheduleproject.data.token.repository

import com.example.sheduleproject.data.token.datasource.LocalTokenDatasource
import com.example.sheduleproject.data.token.datasource.TokenDatasource
import com.example.sheduleproject.data.token.mapper.toEntity
import com.example.sheduleproject.data.token.mapper.toModel
import com.example.sheduleproject.domain.token.entity.TokenEntity
import com.example.sheduleproject.domain.token.repository.TokenRepository

class TokenRepositoryImpl(
    private val localTokenDatasource: LocalTokenDatasource,
    private val remoteTokenDatasource: TokenDatasource
) : TokenRepository {
    override suspend fun refreshToken(tokenEntity: TokenEntity): TokenEntity =
        remoteTokenDatasource.refreshToken(tokenEntity.toModel()).toEntity()

    override fun saveToken(tokenEntity: TokenEntity) =
        localTokenDatasource.saveToken(tokenEntity.toModel())

    override fun getToken(): TokenEntity =
        localTokenDatasource.getToken().toEntity()
}