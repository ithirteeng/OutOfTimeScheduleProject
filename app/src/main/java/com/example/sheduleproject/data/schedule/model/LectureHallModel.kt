package com.example.sheduleproject.data.schedule.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class LectureHallModel(
    @SerializedName("id")
    var id: UUID?,
    @SerializedName("name")
    val name: String,
    @SerializedName("hostBuildingId")
    val hostBuildingId: UUID,
    @SerializedName("capacity")
    val capacity: Int
)
