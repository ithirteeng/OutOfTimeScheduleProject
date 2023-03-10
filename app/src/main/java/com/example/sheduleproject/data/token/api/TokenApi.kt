package com.example.sheduleproject.data.token.api

import com.example.sheduleproject.data.common.model.TokenModel
import retrofit2.http.POST

interface TokenApi {
    @POST("api/auth/refresh")
    suspend fun refreshToken(tokenModel: TokenModel): TokenModel
}