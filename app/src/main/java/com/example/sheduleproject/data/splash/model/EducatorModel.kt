package com.example.sheduleproject.data.splash.model

import com.google.gson.annotations.SerializedName

data class EducatorModel(
    @SerializedName("id")
    var id: String?,
    @SerializedName("firstName")
    var firstName: String?,
    @SerializedName("middleName")
    var middleName: String?,
    @SerializedName("lastName")
    var lastName: String?
)