package com.example.sheduleproject.data.schedulechoice.model

import com.google.gson.annotations.SerializedName

data class CampusBuildingModel(
    @SerializedName("id")
    var id: String?,
    @SerializedName("address")
    var address: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("lectureHallIds")
    var lectureHallIds: List<String?>?
)
