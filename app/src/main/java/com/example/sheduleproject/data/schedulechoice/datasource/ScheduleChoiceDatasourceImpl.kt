package com.example.sheduleproject.data.schedulechoice.datasource

import com.example.sheduleproject.data.schedulechoice.api.ScheduleChoiceApi
import com.example.sheduleproject.data.schedulechoice.model.ClusterModel
import com.example.sheduleproject.data.schedulechoice.model.EducatorModel
import com.example.sheduleproject.data.schedulechoice.model.LectureHallModel

class ScheduleChoiceDatasourceImpl(
    private val api: ScheduleChoiceApi
) : ScheduleChoiceDatasource {
    override suspend fun getClustersList(): List<ClusterModel> =
        api.getClustersList()

    override suspend fun getEducatorList(): List<EducatorModel> =
        api.getEducatorList()

    override suspend fun getLectureHallsList(): List<LectureHallModel> =
        api.getLectureHallsList()

}