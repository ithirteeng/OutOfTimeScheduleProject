package com.example.sheduleproject.domain.schedule.entity

import java.util.UUID

data class EducatorEntity(
    var id: UUID?,
    var firstName: String?,
    var middleName: String?,
    var lastName: String?
)