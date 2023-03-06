package com.example.sheduleproject.domain.schedule.entity

data class ClassEntity(
    var id: String?,
    val timeSlotNumber: Int,
    val clusterNumber: String,
    val date: String,
    var lectureHall: LectureHallEntity?,
    var educator: EducatorEntity?,
    var subject: SubjectEntity?,
    val classTypeName: String?
)
