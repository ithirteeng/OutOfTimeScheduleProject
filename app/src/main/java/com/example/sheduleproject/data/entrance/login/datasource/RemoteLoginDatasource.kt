package com.example.sheduleproject.data.entrance.login.datasource

import com.example.sheduleproject.data.common.model.TokenModel
import com.example.sheduleproject.data.entrance.login.model.LoginModel
import com.example.sheduleproject.data.entrance.login.model.UserModel

interface RemoteLoginDatasource {
    suspend fun postLoginData(loginModel: LoginModel): TokenModel

    suspend fun getUserData(): UserModel
}