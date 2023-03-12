package com.example.sheduleproject.domain.schedule.usecase

import com.example.sheduleproject.domain.schedule.repository.ScheduleRepository

class RemoveTokenUseCase(
    private val repository: ScheduleRepository
) {
    operator fun invoke() =
        repository.removeToken()
}