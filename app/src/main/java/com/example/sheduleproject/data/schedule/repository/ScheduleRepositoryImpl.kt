package com.example.sheduleproject.data.schedule.repository

import com.example.sheduleproject.data.schedule.mapper.toEntitiesList
import com.example.sheduleproject.data.storage.TimeSlotStorage
import com.example.sheduleproject.domain.schedule.entity.ClassEntity
import com.example.sheduleproject.domain.schedule.entity.TimeSlotEntity
import com.example.sheduleproject.domain.schedule.repository.ScheduleRepository
import java.util.*

class ScheduleRepositoryImpl(
    private val timeSlotStorage: TimeSlotStorage
) : ScheduleRepository {
    override fun getTimeSlotListFromStorage(): List<TimeSlotEntity> {
        return timeSlotStorage.getTimeSLotListFromLocalStorage().toEntitiesList()
    }

    override fun getClass(classId: UUID): ClassEntity {
        TODO("Not yet implemented")
    }
}