package com.example.sheduleproject.data.entrance.registration.datasource

import com.example.sheduleproject.data.common.model.TokenModel
import com.example.sheduleproject.data.token.storage.TokenStorage

class LocalRegistrationDatasourceImpl(
    private val storage: TokenStorage
) : LocalRegistrationDatasource {
    override fun saveTokenToLocalStorage(tokenModel: TokenModel) =
        storage.saveTokens(tokenModel)
}