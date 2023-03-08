package com.example.sheduleproject.data.splash.mapper

import com.example.sheduleproject.data.common.mapper.model.TimeSlotModel
import com.example.sheduleproject.domain.common.entity.TimeSlotEntity

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