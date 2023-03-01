package com.example.sheduleproject.data.schedule.model

import java.util.*

data class CampusBuildingModel(
    var id: UUID?,
    var address: String?,
    var name: String?,
    var lectureHalls: List<String?>?
)
