package com.example.sheduleproject.data.token.datasource

import com.example.sheduleproject.data.common.model.TokenModel
import retrofit2.Response

interface TokenDatasource {
    fun refreshToken(tokenModel: TokenModel): Response<TokenModel>
}