package com.example.sheduleproject.data.schedule.model

import com.example.sheduleproject.domain.schedule.entity.CampusBuildingEntity
import com.example.sheduleproject.domain.schedule.entity.EducatorEntity
import com.example.sheduleproject.domain.schedule.entity.LectureHallEntity
import com.example.sheduleproject.domain.schedule.entity.SubjectEntity
import java.util.*

data class ClassModel(
    var id: UUID?,
    val timeSlotNumber: Int,
    val clusterNumber: String,
    val date: String,
    var lectureHall: LectureHallModel?,
    var campusBuilding: CampusBuildingModel?,
    var educator: EducatorModel?,
    var subject: SubjectModel?,
    val classTypeName: String?
)
