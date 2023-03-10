package com.example.sheduleproject.data.entrance.login.datasource

import com.example.sheduleproject.data.common.model.TokenModel
import com.example.sheduleproject.data.entrance.login.model.LoginModel

interface LoginDatasource {
    suspend fun postLoginData(loginModel: LoginModel): TokenModel
}