package com.example.sheduleproject.data.token.datasource

import com.example.sheduleproject.data.common.model.TokenModel
import com.example.sheduleproject.data.token.api.TokenApi

class TokenDatasourceImpl(
    private val api: TokenApi
) : TokenDatasource {
    override suspend fun refreshToken(tokenModel: TokenModel): TokenModel =
        api.refreshToken(tokenModel)

}