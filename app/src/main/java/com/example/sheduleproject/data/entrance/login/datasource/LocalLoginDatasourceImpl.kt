package com.example.sheduleproject.data.entrance.login.datasource

import com.example.sheduleproject.data.common.model.TokenModel
import com.example.sheduleproject.data.common.storage.UserStorage
import com.example.sheduleproject.data.token.storage.TokenStorage

class LocalLoginDatasourceImpl(
    private val tokenStorage: TokenStorage,
    private val userStorage: UserStorage
) : LocalLoginDatasource {
    override fun saveTokenToLocalStorage(tokenModel: TokenModel) =
        tokenStorage.saveTokens(tokenModel)

    override fun setIfUserWasAuthorizedFlag(isAuthorized: Boolean) =
        userStorage.setUserAuthorizationFlag(isAuthorized)

}