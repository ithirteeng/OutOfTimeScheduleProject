package com.example.sheduleproject.domain.schedule.usecase

import com.example.sheduleproject.domain.schedule.entity.ClassEntity
import com.example.sheduleproject.domain.schedule.repository.ScheduleRepository

class GetClassesListUseCase(
    private val repository: ScheduleRepository
) {

    suspend operator fun invoke(
        startDate: String?,
        endDate: String?,
        clusterNumber: String?,
        educatorId: String?,
        dayOfWeek: String?,
        classType: String?
    ): Result<List<ClassEntity>> {
        return repository.getClassesList(
            startDate = startDate,
            endDate = endDate,
            clusterNumber = clusterNumber,
            educatorId = educatorId,
            dayOfWeek = dayOfWeek,
            classType = classType
        )
    }
}