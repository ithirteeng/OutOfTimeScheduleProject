package com.example.sheduleproject.data.entrance.login.api

import com.example.sheduleproject.data.common.model.TokenModel
import com.example.sheduleproject.data.entrance.login.model.LoginModel
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("api/auth/login")
    suspend fun postLoginData(@Body loginModel: LoginModel): TokenModel
}