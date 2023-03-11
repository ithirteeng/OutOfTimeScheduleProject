package com.example.sheduleproject.domain.splash.usecase

import com.example.sheduleproject.domain.common.entity.TimeSlotEntity
import com.example.sheduleproject.domain.splash.repository.SplashRepository

class SaveTimeSlotsListUseCase(
    private val repository: SplashRepository
) {
    operator fun invoke(timeSlotsList: List<TimeSlotEntity>) =
        repository.saveTimeSlotsListToLocalStorage(timeSlotsList)
}