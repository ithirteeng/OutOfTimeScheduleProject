package com.example.sheduleproject.domain.entrance.registration.entity

import com.example.sheduleproject.common.UserType

data class RegistrationEntity(
    var email: String?,
    var lastName: String?,
    var firstName: String?,
    var middleName: String?,
    var accountType: UserType,
    var gradeBookNumber: String?,
    var password: String?,
    var clusterNumber: String?
) : java.io.Serializable