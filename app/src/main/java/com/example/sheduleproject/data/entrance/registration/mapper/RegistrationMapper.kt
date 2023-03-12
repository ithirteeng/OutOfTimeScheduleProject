package com.example.sheduleproject.data.entrance.registration.mapper

import com.example.sheduleproject.data.entrance.registration.model.RegistrationModel
import com.example.sheduleproject.domain.entrance.registration.entity.RegistrationEntity

fun RegistrationEntity.toModel(): RegistrationModel =
    RegistrationModel(
        email = email,
        lastName = lastName,
        firstName = firstName,
        middleName = middleName,
        accountType = accountType?.getString() ?: "",
        gradeBookNumber = gradeBookNumber,
        password = password,
        clusterNumber = clusterNumber
    )