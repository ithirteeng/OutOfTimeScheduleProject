package com.example.sheduleproject.data.entrance.registration.datasource

import com.example.sheduleproject.data.common.model.TokenModel
import com.example.sheduleproject.data.entrance.registration.api.RegistrationApi
import com.example.sheduleproject.data.entrance.registration.model.RegistrationModel

class RemoteRegistrationDatasourceImpl(
    private val api: RegistrationApi
) : RemoteRegistrationDatasource {
    override suspend fun postRegistrationData(registrationModel: RegistrationModel): TokenModel =
        api.postRegistrationData(registrationModel)
}