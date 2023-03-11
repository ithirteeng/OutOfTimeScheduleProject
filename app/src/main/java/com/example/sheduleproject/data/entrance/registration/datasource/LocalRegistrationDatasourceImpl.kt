package com.example.sheduleproject.data.entrance.registration.datasource

import com.example.sheduleproject.data.common.model.TokenModel
import com.example.sheduleproject.data.common.storage.UserStorage
import com.example.sheduleproject.data.token.storage.TokenStorage

class LocalRegistrationDatasourceImpl(
    private val tokenStorage: TokenStorage,
    private val userStorage: UserStorage
) : LocalRegistrationDatasource {

    override fun saveTokenToLocalStorage(tokenModel: TokenModel) =
        tokenStorage.saveTokens(tokenModel)

    override fun setIfUserWasAuthorizedFlag(authorizationFlag: Boolean) =
        userStorage.setUserAuthorizationFlag(authorizationFlag)
}