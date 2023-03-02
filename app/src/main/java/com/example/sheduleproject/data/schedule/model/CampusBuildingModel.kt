package com.example.sheduleproject.data.schedule.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class CampusBuildingModel(
    @SerializedName("id")
    var id: UUID?,
    @SerializedName("address")
    var address: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("lectureHalls")
    var lectureHalls: List<String?>?
)
