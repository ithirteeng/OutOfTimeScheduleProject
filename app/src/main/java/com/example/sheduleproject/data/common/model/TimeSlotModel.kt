package com.example.sheduleproject.data.common.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TimeSlotModel(
    @SerializedName("number")
    val number: Int,
    @SerializedName("startTime")
    val startTime: String,
    @SerializedName("endTime")
    val endTime: String
) : Serializable