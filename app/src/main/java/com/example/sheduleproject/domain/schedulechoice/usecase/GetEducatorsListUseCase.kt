package com.example.sheduleproject.domain.schedulechoice.usecase

import com.example.sheduleproject.domain.schedulechoice.entity.EducatorEntity
import com.example.sheduleproject.domain.schedulechoice.repository.ScheduleChoiceRepository

class GetEducatorsListUseCase(
    private val repository: ScheduleChoiceRepository
) {
    suspend operator fun invoke(): Result<List<EducatorEntity>> =
        repository.getEducatorList()
}