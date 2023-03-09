package com.example.sheduleproject.data.token.datasource

import com.example.sheduleproject.data.common.model.TokenModel
import com.example.sheduleproject.data.token.api.TokenApi
import retrofit2.Response

class TokenDatasourceImpl(
    private val api: TokenApi
) : TokenDatasource {
    override fun refreshToken(tokenModel: TokenModel): Response<TokenModel> =
        api.refreshToken(tokenModel)

}