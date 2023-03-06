package com.example.sheduleproject.presentation.schedule

import com.example.sheduleproject.domain.schedule.entity.*

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
    hostBuilding = CampusBuildingEntity(
        null, null, null, null
    ),
    capacity = 100
)

val classEntity1 = ClassEntity(
    id = null,
    timeSlotNumber = 1,
    clusterNumber = "972101",
    date = "today",
    lectureHall = lectureHallEntity,
    educator = educatorEntity,
    subject = subjectEntity,
    classTypeName = "Exam"
)
val classEntity2 = ClassEntity(
    id = null,
    timeSlotNumber = 2,
    clusterNumber = "972101",
    date = "today",
    lectureHall = lectureHallEntity,
    educator = educatorEntity,
    subject = subjectEntity,
    classTypeName = "Exam"
)
val classEntity5 = ClassEntity(
    id = null,
    timeSlotNumber = 5,
    clusterNumber = "972101",
    date = "today",
    lectureHall = lectureHallEntity,
    educator = educatorEntity,
    subject = subjectEntity,
    classTypeName = "Seminar"
)
val classEntity6 = ClassEntity(
    id = null,
    timeSlotNumber = 6,
    clusterNumber = "972101",
    date = "today",
    lectureHall = lectureHallEntity,
    educator = educatorEntity,
    subject = subjectEntity,
    classTypeName = "Exam"
)
val classEntity7 = ClassEntity(
    id = null,
    timeSlotNumber = 7,
    clusterNumber = "972101",
    date = "today",
    lectureHall = lectureHallEntity,
    educator = educatorEntity,
    subject = subjectEntity,
    classTypeName = "Practice"
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

val listOfEntities = arrayListOf(
    classEntity1, classEntity5, classEntity2, classEntity7, classEntity6
)