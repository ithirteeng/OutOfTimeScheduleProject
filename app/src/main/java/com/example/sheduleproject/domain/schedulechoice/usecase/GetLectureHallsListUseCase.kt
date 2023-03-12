package com.example.sheduleproject.domain.schedulechoice.usecase

import com.example.sheduleproject.domain.schedulechoice.entity.LectureHallEntity
import com.example.sheduleproject.domain.schedulechoice.repository.ScheduleChoiceRepository

class GetLectureHallsListUseCase(
    private val repository: ScheduleChoiceRepository
) {
    suspend operator fun invoke(): Result<List<LectureHallEntity>> =
        repository.getLectureHallsList()
}