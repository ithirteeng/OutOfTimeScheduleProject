package com.example.sheduleproject.data.token.datasource

import com.example.sheduleproject.data.common.model.TokenModel
import com.example.sheduleproject.data.token.storage.TokenStorage

class LocalTokenDatasourceImpl(
    private val storage: TokenStorage
) : LocalTokenDatasource {
    override fun saveToken(tokenModel: TokenModel?) {
        storage.saveTokens(tokenModel)
    }

    override fun getToken(): TokenModel {
        return storage.getTokens()
    }
}