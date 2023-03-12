package com.example.sheduleproject.data.schedule.api

import com.example.sheduleproject.data.schedule.model.ClassModel
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ScheduleApi {
    @GET("api/class/query")
    suspend fun getClassesList(
        @Query("startDate") startDate: String?,
        @Query("endDate") endDate: String?,
        @Query("clusterNumber") clusterNumber: String?,
        @Query("lectureHallId") lectureHallId: String?,
        @Query("educatorId") educatorId: String?,
        @Query("dayOfWeek") dayOfWeek: String?,
        @Query("classType") classType: String?
    ): List<ClassModel>

    @GET("api/class/{id}")
    suspend fun getClassInfo(@Path("id") classId: String): ClassModel

    @POST("api/auth/logout")
    suspend fun logout()
}