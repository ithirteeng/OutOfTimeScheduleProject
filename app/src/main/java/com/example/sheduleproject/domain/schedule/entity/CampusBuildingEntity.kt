package com.example.sheduleproject.domain.schedule.entity

data class CampusBuildingEntity(
    var id: String?,
    var address: String?,
    var name: String?,
    var lectureHallIds: List<String?>?
)