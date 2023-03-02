package com.example.sheduleproject.data.schedule.mapper

import com.example.sheduleproject.data.schedule.model.*
import com.example.sheduleproject.domain.schedule.entity.*

fun List<TimeSlotModel>.toEntitiesList(): List<TimeSlotEntity> =
    map { it.toEntity() }

fun List<TimeSlotEntity>.toModelsList(): List<TimeSlotModel> =
    map { it.toModel() }

private fun TimeSlotModel.toEntity(): TimeSlotEntity =
    TimeSlotEntity(
        number = number,
        startTime = startTime,
        endTime = endTime
    )

private fun TimeSlotEntity.toModel(): TimeSlotModel =
    TimeSlotModel(
        number = number,
        startTime = startTime,
        endTime = endTime
    )

@JvmName("toEntitiesListClassModel")
fun List<ClassModel>.toEntitiesList(): List<ClassEntity> =
    map { it.toEntity() }

@JvmName("toModelsListClassEntity")
fun List<ClassEntity>.toModelsList(): List<ClassModel> =
    map { it.toModel() }

fun ClassModel.toEntity(): ClassEntity =
    ClassEntity(
        id = id,
        timeSlotNumber = timeSlotNumber,
        clusterNumber = clusterNumber,
        date = date,
        lectureHall = lectureHall?.toEntity(),
        campusBuilding = campusBuilding?.toEntity(),
        educator = educator?.toEntity(),
        subject = subject?.toEntity(),
        classTypeName = classTypeName
    )

fun ClassEntity.toModel(): ClassModel =
    ClassModel(
        id = id,
        timeSlotNumber = timeSlotNumber,
        clusterNumber = clusterNumber,
        date = date,
        lectureHall = lectureHall?.toModel(),
        campusBuilding = campusBuilding?.toModel(),
        educator = educator?.toModel(),
        subject = subject?.toModel(),
        classTypeName = classTypeName
    )

private fun CampusBuildingModel.toEntity(): CampusBuildingEntity =
    CampusBuildingEntity(
        id = id,
        address = address,
        name = name,
        lectureHalls = lectureHalls
    )

private fun CampusBuildingEntity.toModel(): CampusBuildingModel =
    CampusBuildingModel(
        id = id,
        address = address,
        name = name,
        lectureHalls = lectureHalls
    )

private fun SubjectModel.toEntity(): SubjectEntity =
    SubjectEntity(
        id = id,
        name = name
    )

private fun SubjectEntity.toModel(): SubjectModel =
    SubjectModel(
        id = id,
        name = name
    )

private fun EducatorModel.toEntity(): EducatorEntity =
    EducatorEntity(
        id = id,
        firstName = firstName,
        middleName = middleName,
        lastName = lastName
    )

private fun EducatorEntity.toModel(): EducatorModel =
    EducatorModel(
        id = id,
        firstName = firstName,
        middleName = middleName,
        lastName = lastName
    )

private fun LectureHallModel.toEntity(): LectureHallEntity =
    LectureHallEntity(
        id = id,
        name = name,
        hostBuildingId = hostBuildingId,
        capacity = capacity
    )

private fun LectureHallEntity.toModel(): LectureHallModel =
    LectureHallModel(
        id = id,
        name = name,
        hostBuildingId = hostBuildingId,
        capacity = capacity
    )