package com.example.sheduleproject.domain.schedule.repository

import com.example.sheduleproject.domain.common.entity.TimeSlotEntity
import com.example.sheduleproject.domain.schedule.entity.ClassEntity
import java.util.*

interface ScheduleRepository {

    fun getTimeSlotListFromStorage(): List<TimeSlotEntity>

    fun getClass(classId: UUID): ClassEntity

}