package com.example.sheduleproject.domain.schedule.usecase

import com.example.sheduleproject.domain.schedule.repository.ScheduleRepository

class SetUserAuthorizationFlagUseCase(
    private val repository: ScheduleRepository
) {
    operator fun invoke(authorizationFlag: Boolean) =
        repository.setUserAuthorizationFlag(authorizationFlag)
}