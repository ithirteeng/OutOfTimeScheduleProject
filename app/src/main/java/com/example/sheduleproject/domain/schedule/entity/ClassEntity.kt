package com.example.sheduleproject.domain.schedule.entity

import java.util.*

data class ClassEntity(
    var id: UUID?,
    val timeSlotNumber: Int,
    val clusterNumber: String,
    val date: String,
    var lectureHall: LectureHallEntity?,
    var campusBuilding: CampusBuildingEntity?,
    var educator: EducatorEntity?,
    var subject: SubjectEntity?,
    val classTypeName: String?
)
