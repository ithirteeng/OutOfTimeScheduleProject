package com.example.sheduleproject.data.token.datasource

import com.example.sheduleproject.data.common.model.TokenModel

interface LocalTokenDatasource {
    fun saveToken(tokenModel: TokenModel?)

    fun getToken(): TokenModel?
}