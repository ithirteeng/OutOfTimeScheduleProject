package com.example.sheduleproject.data.schedule.mapper

import com.example.sheduleproject.data.common.mapper.model.TimeSlotModel
import com.example.sheduleproject.data.schedule.model.*
import com.example.sheduleproject.domain.common.entity.TimeSlotEntity
import com.example.sheduleproject.domain.schedule.entity.*

fun List<TimeSlotModel>.toEntitiesList(): List<TimeSlotEntity> =
    map { it.toEntity() }

private fun TimeSlotModel.toEntity(): TimeSlotEntity =
    TimeSlotEntity(
        number = number,
        startTime = startTime,
        endTime = endTime
    )

@JvmName("toEntitiesListClassModel")
fun List<ClassModel>.toEntitiesList(): List<ClassEntity> =
    map { it.toEntity() }

fun List<ClassEntity>.toModelsList(): List<ClassModel> =
    map { it.toModel() }


fun ClassModel.toEntity(): ClassEntity =
    ClassEntity(
        id = id,
        timeSlotNumber = timeSlotNumber,
        clusterNumber = clusterNumber,
        date = date,
        lectureHall = lectureHall?.toEntity(),
        educator = educator?.toEntity(),
        subject = subject?.toEntity(),
        classTypeName = classTypeName
    )

private fun ClassEntity.toModel(): ClassModel =
    ClassModel(
        id = id,
        timeSlotNumber = timeSlotNumber,
        clusterNumber = clusterNumber,
        date = date,
        lectureHall = lectureHall?.toModel(),
        educator = educator?.toModel(),
        subject = subject?.toModel(),
        classTypeName = classTypeName
    )


private fun CampusBuildingModel.toEntity(): CampusBuildingEntity =
    CampusBuildingEntity(
        id = id,
        address = address,
        name = name,
        lectureHallIds = lectureHallIds
    )

private fun CampusBuildingEntity.toModel(): CampusBuildingModel =
    CampusBuildingModel(
        id = id,
        address = address,
        name = name,
        lectureHallIds = lectureHallIds
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
        hostBuilding = hostBuilding.toEntity(),
        capacity = capacity
    )

private fun LectureHallEntity.toModel(): LectureHallModel =
    LectureHallModel(
        id = id,
        name = name,
        hostBuilding = hostBuilding.toModel(),
        capacity = capacity
    )
