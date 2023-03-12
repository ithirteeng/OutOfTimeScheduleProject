package com.example.sheduleproject.data.splash.mapper

import com.example.sheduleproject.data.common.model.TimeSlotModel
import com.example.sheduleproject.data.splash.model.EducatorModel
import com.example.sheduleproject.data.splash.model.UserModel
import com.example.sheduleproject.domain.common.entity.TimeSlotEntity
import com.example.sheduleproject.domain.splash.entity.EducatorEntity
import com.example.sheduleproject.domain.splash.entity.UserEntity


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

fun List<TimeSlotEntity>.toModelsList(): List<TimeSlotModel> =
    map { it.toModel() }

fun List<TimeSlotModel>.toEntitiesList(): List<TimeSlotEntity> =
    map { it.toEntity() }

private fun EducatorModel.toEntity(): EducatorEntity =
    EducatorEntity(
        id = id,
        firstName = firstName,
        middleName = middleName,
        lastName = lastName
    )

fun UserModel.toEntity(): UserEntity =
    UserEntity(
        id = id,
        email = email,
        password = password,
        accountType = accountType,
        claimedRoles = claimedRoles,
        verifiedRoles = verifiedRoles,
        firstName = firstName,
        middleName = middleName,
        lastName = lastName,
        gradeBookNumber = gradeBookNumber,
        clusterNumber = clusterNumber,
        scheduleSelf = scheduleSelf?.toEntity()
    )