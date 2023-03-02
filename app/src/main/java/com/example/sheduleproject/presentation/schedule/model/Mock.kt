package com.example.sheduleproject.presentation.schedule.model

import com.example.sheduleproject.domain.schedule.entity.ClassEntity
import com.example.sheduleproject.domain.schedule.entity.EducatorEntity
import com.example.sheduleproject.domain.schedule.entity.LectureHallEntity
import com.example.sheduleproject.domain.schedule.entity.SubjectEntity
import java.util.*

val educatorEntity = EducatorEntity(
    id = null,
    firstName = "CHEL",
    middleName = "CHELOV",
    lastName = "CHELOVICH"
)

val subjectEntity = SubjectEntity(
    id = null,
    name = "Math"
)

val lectureHallEntity = LectureHallEntity(
    id = null,
    name = "1231",
    hostBuildingId = UUID.randomUUID(),
    capacity = 100
)
//
//val classEntity1 = ClassEntity(
//    id = null,
//    timeSlotNumber = 1,
//    date = "Pofig",
//    lectureHall = lectureHallEntity,
//    educator = educatorEntity,
//    subject = subjectEntity,
//    classTypeName = "Practice"
//)
//
//val classEntity2 = ClassEntity(
//    id = null,
//    timeSlotNumber = 1,
//    date = "Pofig",
//    lectureHall = lectureHallEntity,
//    educator = educatorEntity,
//    subject = subjectEntity,
//    classTypeName = "Seminar"
//)
//
//val classEntity3 = ClassEntity(
//    id = null,
//    timeSlotNumber = 1,
//    date = "Pofig",
//    lectureHall = lectureHallEntity,
//    educator = educatorEntity,
//    subject = subjectEntity,
//    classTypeName = "Exam"
//)

val listOfEntities = arrayListOf<ClassEntity>(

)