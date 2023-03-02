package com.example.sheduleproject.data.schedule.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class ClassModel(
    @SerializedName("id")
    var id: UUID?,
    @SerializedName("timeSlotNumber")
    val timeSlotNumber: Int,
    @SerializedName("clusterNumber")
    val clusterNumber: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("lectureHall")
    var lectureHall: LectureHallModel?,
    @SerializedName("campusBuilding")
    var campusBuilding: CampusBuildingModel?,
    @SerializedName("educator")
    var educator: EducatorModel?,
    @SerializedName("subject")
    var subject: SubjectModel?,
    @SerializedName("classTypeName")
    val classTypeName: String?
)
