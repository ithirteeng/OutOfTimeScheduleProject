package com.example.sheduleproject.domain.schedule.usecase

import com.example.sheduleproject.domain.schedule.repository.ScheduleRepository

class CheckTokenExistenceUseCase(
    private val repository: ScheduleRepository
) {
    operator fun invoke(): Boolean =
        repository.checkTokenExistence()
}