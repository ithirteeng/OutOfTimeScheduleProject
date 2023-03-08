package com.example.sheduleproject.data.entrance.registration.datasource

import com.example.sheduleproject.data.common.model.TokenModel
import com.example.sheduleproject.data.entrance.registration.model.RegistrationModel

interface RegistrationDatasource {
    suspend fun postRegistrationData(registrationModel: RegistrationModel): TokenModel
}