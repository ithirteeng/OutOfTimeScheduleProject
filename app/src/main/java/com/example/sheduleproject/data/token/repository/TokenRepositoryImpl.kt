package com.example.sheduleproject.data.token.repository

import com.example.sheduleproject.data.common.model.TokenModel
import com.example.sheduleproject.data.token.datasource.LocalTokenDatasource
import com.example.sheduleproject.data.token.datasource.TokenDatasource
import com.example.sheduleproject.data.token.mapper.toEntity
import com.example.sheduleproject.data.token.mapper.toModel
import com.example.sheduleproject.domain.token.entity.TokenEntity
import com.example.sheduleproject.domain.token.repository.TokenRepository
import retrofit2.Response

class TokenRepositoryImpl(
    private val localTokenDatasource: LocalTokenDatasource,
    private val remoteTokenDatasource: TokenDatasource
) : TokenRepository {
    override fun refreshToken(tokenEntity: TokenEntity): Response<TokenModel> =
        remoteTokenDatasource.refreshToken(tokenEntity.toModel())

    override fun saveToken(tokenEntity: TokenEntity) =
        localTokenDatasource.saveToken(tokenEntity.toModel())

    override fun getToken(): TokenEntity =
        localTokenDatasource.getToken().toEntity()
}