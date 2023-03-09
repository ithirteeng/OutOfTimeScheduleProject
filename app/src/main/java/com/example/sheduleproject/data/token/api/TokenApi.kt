package com.example.sheduleproject.data.token.api

import com.example.sheduleproject.data.common.model.TokenModel
import retrofit2.Response
import retrofit2.http.GET

interface TokenApi {
    @GET("api/auth/refresh")
    fun refreshToken(tokenModel: TokenModel): Response<TokenModel>
}