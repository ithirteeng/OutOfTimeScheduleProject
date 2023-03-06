package com.example.sheduleproject.data.schedule.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class SubjectModel(
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?
)