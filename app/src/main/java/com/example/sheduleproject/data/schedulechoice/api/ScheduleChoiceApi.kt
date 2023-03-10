package com.example.sheduleproject.data.schedulechoice.api

import com.example.sheduleproject.data.schedulechoice.model.ClusterModel
import com.example.sheduleproject.data.schedulechoice.model.EducatorModel
import com.example.sheduleproject.data.schedulechoice.model.LectureHallModel
import retrofit2.http.GET

interface ScheduleChoiceApi {
    @GET("api/cluster")
    suspend fun getClustersList(): List<ClusterModel>

    @GET("api/educator")
    suspend fun getEducatorList(): List<EducatorModel>

    @GET("api/lecture-hall")
    suspend fun getLectureHallsList(): List<LectureHallModel>
}