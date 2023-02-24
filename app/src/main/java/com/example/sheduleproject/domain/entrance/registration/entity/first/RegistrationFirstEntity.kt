package com.example.sheduleproject.domain.entrance.registration.entity.first

import java.io.Serializable

data class RegistrationFirstEntity(
    val email: String,
    val surname: String,
    val name: String,
    val patronymic: String,
) : Serializable
