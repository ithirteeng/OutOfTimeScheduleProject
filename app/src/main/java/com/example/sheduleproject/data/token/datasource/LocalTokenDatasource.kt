package com.example.sheduleproject.data.token.datasource

import com.example.sheduleproject.data.common.model.TokenModel

interface LocalTokenDatasource {
    fun getToken(): TokenModel
    fun saveToken(tokenModel: TokenModel)
}