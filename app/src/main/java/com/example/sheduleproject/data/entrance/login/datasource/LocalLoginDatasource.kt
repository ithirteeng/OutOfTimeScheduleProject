package com.example.sheduleproject.data.entrance.login.datasource

import com.example.sheduleproject.data.common.model.TokenModel

interface LocalLoginDatasource {
    fun saveTokenToLocalStorage(tokenModel: TokenModel)

    fun setIfUserWasAuthorizedFlag(isAuthorized: Boolean)
}