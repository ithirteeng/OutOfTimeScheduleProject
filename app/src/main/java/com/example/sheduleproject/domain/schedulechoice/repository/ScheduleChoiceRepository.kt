package com.example.sheduleproject.domain.schedulechoice.repository

import com.example.sheduleproject.domain.schedulechoice.entity.ClusterEntity
import com.example.sheduleproject.domain.schedulechoice.entity.EducatorEntity
import com.example.sheduleproject.domain.schedulechoice.entity.LectureHallEntity

interface ScheduleChoiceRepository {
    suspend fun getClustersList(): Result<List<ClusterEntity>>

    suspend fun getEducatorList(): Result<List<EducatorEntity>>

    suspend fun getLectureHallsList(): Result<List<LectureHallEntity>>
}