package com.example.sheduleproject.data.entrance.registration.model

import com.google.gson.annotations.SerializedName

data class RegistrationModel(
    @SerializedName("email")
    var email: String?,
    @SerializedName("lastName")
    var lastName: String?,
    @SerializedName("firstName")
    var firstName: String?,
    @SerializedName("middleName")
    var middleName: String?,
    @SerializedName("accountType")
    val accountType: String,
    @SerializedName("gradeBookNumber")
    var gradeBookNumber: String?,
    @SerializedName("password")
    var password: String?,
    @SerializedName("clusterNumber")
    var clusterNumber: String?
)
