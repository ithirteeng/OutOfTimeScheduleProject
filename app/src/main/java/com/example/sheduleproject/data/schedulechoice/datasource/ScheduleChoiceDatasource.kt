package com.example.sheduleproject.data.schedulechoice.datasource

import com.example.sheduleproject.data.schedulechoice.model.ClusterModel
import com.example.sheduleproject.data.schedulechoice.model.EducatorModel
import com.example.sheduleproject.data.schedulechoice.model.LectureHallModel

interface ScheduleChoiceDatasource {
    suspend fun getClustersList(): List<ClusterModel>

    suspend fun getEducatorList(): List<EducatorModel>

    suspend fun getLectureHallsList(): List<LectureHallModel>
}