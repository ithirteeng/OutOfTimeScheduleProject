package com.example.sheduleproject.domain.entrance.login.usecase

import com.example.sheduleproject.domain.entrance.login.repository.LoginRepository

class SetUserAuthorizationFlagUseCase(
    private val repository: LoginRepository
) {
    operator fun invoke(authorizationFlag: Boolean) =
        repository.setIfUserWasAuthorizedFlag(authorizationFlag)
}