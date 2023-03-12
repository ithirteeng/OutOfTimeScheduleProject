package com.example.sheduleproject.data.entrance.login.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("email")
    var email: String?,
    @SerializedName("password")
    var password: String?,
    @SerializedName("accountType")
    val accountType: String,
    @SerializedName("claimedRoles")
    var claimedRoles: List<String>?,
    @SerializedName("verifiedRoles")
    var verifiedRoles: List<String>?,
    @SerializedName("firstName")
    var firstName: String?,
    @SerializedName("middleName")
    var middleName: String?,
    @SerializedName("lastName")
    var lastName: String?,
    @SerializedName("gradeBookNumber")
    var gradeBookNumber: String?,
    @SerializedName("clusterNumber")
    var clusterNumber: String?,
    @SerializedName("scheduleSelf")
    var scheduleSelf: EducatorModel?
)
