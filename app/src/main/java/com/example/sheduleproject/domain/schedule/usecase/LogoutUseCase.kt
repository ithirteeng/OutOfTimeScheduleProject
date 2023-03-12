package com.example.sheduleproject.domain.schedule.usecase

import com.example.sheduleproject.domain.schedule.repository.ScheduleRepository

class LogoutUseCase(
    private val repository: ScheduleRepository
) {
    suspend operator fun invoke(): Result<Boolean> =
        repository.logout()
}