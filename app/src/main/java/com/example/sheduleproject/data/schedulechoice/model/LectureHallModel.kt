package com.example.sheduleproject.data.schedulechoice.model

import com.google.gson.annotations.SerializedName

data class LectureHallModel(
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("hostBuilding")
    val hostBuilding: CampusBuildingModel,
    @SerializedName("capacity")
    val capacity: Int
)