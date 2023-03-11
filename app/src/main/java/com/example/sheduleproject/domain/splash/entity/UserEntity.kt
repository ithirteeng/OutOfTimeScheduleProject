package com.example.sheduleproject.domain.splash.entity

data class UserEntity(
    val id: String,
    var email: String?,
    var password: String?,
    val accountType: String,
    var claimedRoles: List<String>?,
    var verifiedRoles: List<String>?,
    var firstName: String?,
    var middleName: String?,
    var lastName: String?,
    var gradeBookNumber: String?,
    var clusterNumber: String?,
    var scheduleSelf: EducatorEntity?
)