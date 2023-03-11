package com.example.sheduleproject.data.entrance.login.datasource

import com.example.sheduleproject.data.common.model.TokenModel
import com.example.sheduleproject.data.token.storage.TokenStorage

class LocalLoginDatasourceImpl(
    private val tokenStorage: TokenStorage
) : LocalLoginDatasource {
    override fun saveTokenToLocalStorage(tokenModel: TokenModel) =
        tokenStorage.saveTokens(tokenModel)

}