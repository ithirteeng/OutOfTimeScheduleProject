package com.example.sheduleproject.data.token.datasource

import com.example.sheduleproject.data.common.model.TokenModel

interface TokenDatasource {
    suspend fun refreshToken(tokenModel: TokenModel): TokenModel
}