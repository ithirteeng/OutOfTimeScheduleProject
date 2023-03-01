package com.example.sheduleproject.domain.schedule.entity

import java.util.*

data class CampusBuildingEntity(
    var id: UUID?,
    var address: String?,
    var name: String?,
    var lectureHalls: List<String?>?
)