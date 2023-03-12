package com.example.sheduleproject.data.token.datasource

import com.example.sheduleproject.data.common.model.TokenModel
import com.example.sheduleproject.data.token.api.TokenApi

class RemoteTokenDatasourceImpl(
    private val api: TokenApi
) : RemoteTokenDatasource {
    override suspend fun refreshToken(tokenModel: TokenModel): TokenModel {
        return api.refreshToken(tokenModel)
    }
}