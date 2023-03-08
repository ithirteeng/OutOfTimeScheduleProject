package com.example.sheduleproject.data.token.api

import com.example.sheduleproject.data.common.model.TokenModel
import retrofit2.http.GET

interface TokenApi {
    @GET("api/auth/refresh")
    suspend fun refreshToken(tokenModel: TokenModel): TokenModel
}