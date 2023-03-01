package com.example.sheduleproject.domain.schedule.entity

import java.util.UUID

data class LectureHallEntity(
    var id: UUID?,
    val name: String,
    val hostBuildingId: UUID,
    val capacity: Int
)
