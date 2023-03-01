package com.example.sheduleproject.data.schedule.model

import java.util.*

data class LectureHallModel(
    var id: UUID?,
    val name: String,
    val hostBuildingId: UUID,
    val capacity: Int
)
