package com.example.sheduleproject.domain.schedulechoice.entity

data class LectureHallEntity(
    var id: String?,
    val name: String,
    val hostBuilding: CampusBuildingEntity,
    val capacity: Int
)
