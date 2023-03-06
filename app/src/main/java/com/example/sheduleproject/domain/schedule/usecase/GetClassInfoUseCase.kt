package com.example.sheduleproject.domain.schedule.usecase

import com.example.sheduleproject.domain.schedule.entity.ClassEntity
import com.example.sheduleproject.domain.schedule.repository.ScheduleRepository

class GetClassInfoUseCase(
    private val repository: ScheduleRepository
) {
    suspend operator fun invoke(classId: String): Result<ClassEntity> =
        repository.getClassInfo(classId)
}