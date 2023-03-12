package com.example.sheduleproject.domain.schedule.usecase

import com.example.sheduleproject.domain.schedule.entity.ClassEntity
import com.example.sheduleproject.domain.schedule.repository.ScheduleRepository

class SaveClassesListToLocalStorageUseCase(
    private val repository: ScheduleRepository
) {
    operator fun invoke(classesList: List<ClassEntity>) =
        repository.saveClassesListToLocalStorage(classesList)
}