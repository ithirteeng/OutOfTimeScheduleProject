package com.example.sheduleproject.data.entrance.registration.api

import com.example.sheduleproject.data.common.model.TokenModel
import com.example.sheduleproject.data.entrance.registration.model.RegistrationModel
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationApi {
    @POST("api/auth/register")
    suspend fun postRegistrationData(@Body registrationModel: RegistrationModel): TokenModel
}