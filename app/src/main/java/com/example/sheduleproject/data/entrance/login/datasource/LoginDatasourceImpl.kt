package com.example.sheduleproject.data.entrance.login.datasource

import com.example.sheduleproject.data.common.model.TokenModel
import com.example.sheduleproject.data.entrance.login.api.LoginApi
import com.example.sheduleproject.data.entrance.login.model.LoginModel

class LoginDatasourceImpl(
    private val loginApi: LoginApi
) : LoginDatasource {
    override suspend fun postLoginData(loginModel: LoginModel): TokenModel =
        loginApi.postLoginData(loginModel)
}