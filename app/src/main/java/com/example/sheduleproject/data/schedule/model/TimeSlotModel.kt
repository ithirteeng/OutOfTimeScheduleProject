package com.example.sheduleproject.data.schedule.model

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