package com.example.sheduleproject.domain.schedulechoice.usecase

import com.example.sheduleproject.domain.schedulechoice.entity.ClusterEntity
import com.example.sheduleproject.domain.schedulechoice.repository.ScheduleChoiceRepository

class GetClustersListUseCase(
    private val repository: ScheduleChoiceRepository
) {
    suspend operator fun invoke(): Result<List<ClusterEntity>> =
        repository.getClustersList()
}