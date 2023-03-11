package com.example.sheduleproject.data.entrance.registration.datasource

import com.example.sheduleproject.data.common.model.TokenModel

interface LocalRegistrationDatasource {
    fun saveTokenToLocalStorage(tokenModel: TokenModel)
}