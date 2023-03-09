package com.example.sheduleproject.domain.schedulechoice.entity

data class CampusBuildingEntity(
    var id: String?,
    var address: String?,
    var name: String?,
    var lectureHallIds: List<String?>?
)
