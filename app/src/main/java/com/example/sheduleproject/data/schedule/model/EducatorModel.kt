package com.example.sheduleproject.data.schedule.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class EducatorModel(
    @SerializedName("id")
    var id: UUID?,
    @SerializedName("firstName")
    var firstName: String?,
    @SerializedName("middleName")
    var middleName: String?,
    @SerializedName("lastName")
    var lastName: String?
)