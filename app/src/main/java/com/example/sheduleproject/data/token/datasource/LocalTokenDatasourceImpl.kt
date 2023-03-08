package com.example.sheduleproject.data.token.datasource

import com.example.sheduleproject.data.common.model.TokenModel
import com.example.sheduleproject.data.token.storage.TokenStorage

class LocalTokenDatasourceImpl(
    private val tokenStorage: TokenStorage
) : LocalTokenDatasource {
    override fun getToken(): TokenModel =
        tokenStorage.getTokens()

    override fun saveToken(tokenModel: TokenModel) =
        tokenStorage.saveTokens(tokenModel)
}