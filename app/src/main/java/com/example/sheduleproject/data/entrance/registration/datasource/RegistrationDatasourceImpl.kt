package com.example.sheduleproject.data.entrance.registration.datasource

import com.example.sheduleproject.data.common.mapper.model.TokenModel
import com.example.sheduleproject.data.entrance.registration.api.RegistrationApi
import com.example.sheduleproject.data.entrance.registration.model.RegistrationModel

class RegistrationDatasourceImpl(
    private val api: RegistrationApi
) : RegistrationDatasource {
    override suspend fun postRegistrationData(registrationModel: RegistrationModel): TokenModel =
        api.postRegistrationData(registrationModel)
}