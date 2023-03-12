package com.example.sheduleproject.data.entrance.login.datasource

import com.example.sheduleproject.data.common.model.TokenModel
import com.example.sheduleproject.data.entrance.login.api.LoginApi
import com.example.sheduleproject.data.entrance.login.model.LoginModel
import com.example.sheduleproject.data.entrance.login.model.UserModel

class RemoteLoginDatasourceImpl(
    private val loginApi: LoginApi
) : RemoteLoginDatasource {
    override suspend fun postLoginData(loginModel: LoginModel): TokenModel =
        loginApi.postLoginData(loginModel)

    override suspend fun getUserData(): UserModel =
        loginApi.getUserData()
}