package com.example.sheduleproject.domain.entrance.registration.usecase.second

import com.example.sheduleproject.domain.entrance.registration.repository.RegistrationRepository

class SetUserAuthorizationFlagUseCase(
    private val repository: RegistrationRepository
) {
    operator fun invoke(authorizationFlag: Boolean) =
        repository.setIfUserWasAuthorizedFlag(authorizationFlag)
}