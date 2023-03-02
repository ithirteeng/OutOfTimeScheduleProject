package com.example.sheduleproject.domain.schedule.usecase

import com.example.sheduleproject.domain.schedule.entity.TimeSlotEntity
import com.example.sheduleproject.domain.schedule.repository.ScheduleRepository

class GetTimeSlotListUseCase(
    private val repository: ScheduleRepository
) {
    operator fun invoke(): List<TimeSlotEntity> =
        repository.getTimeSlotListFromStorage()
}