package com.example.sheduleproject.domain.schedule.usecase

import com.example.sheduleproject.domain.schedule.entity.ClassEntity
import com.example.sheduleproject.domain.schedule.repository.ScheduleRepository

class GetClassesListByDateFromStorageUseCase(
    private val repository: ScheduleRepository
) {
    operator fun invoke(date: String): List<ClassEntity> =
        repository.getClassesListByDate(date)
}