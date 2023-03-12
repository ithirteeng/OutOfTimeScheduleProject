package com.example.sheduleproject.domain.splash.usecase

import com.example.sheduleproject.domain.common.entity.TimeSlotEntity
import com.example.sheduleproject.domain.splash.repository.SplashRepository

class GetTimeslotsListUseCase(
    private val repository: SplashRepository
) {
    suspend operator fun invoke(): Result<List<TimeSlotEntity>?> =
        repository.getTimeSlotsList()


}