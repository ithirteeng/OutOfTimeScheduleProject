package com.example.sheduleproject.data.schedule.storage

import android.content.Context
import com.example.sheduleproject.domain.schedule.entity.TimeSlotEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class TimeSlotStorage(context: Context) {

    companion object {
        const val TIMESLOT_KEY = "timeslot key"
        const val STORAGE_NAME = "storage name"
    }

    private val sharedPreferences = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)

    fun saveTimeSlotsListToLocalStorage(timeSlotList: List<TimeSlotEntity>) {
        val json = Gson().toJson(timeSlotList)
        sharedPreferences.edit()
            .putString(TIMESLOT_KEY, json)
            .apply()
    }

    fun getTimeSLotListFromLocalStorage(): List<TimeSlotEntity> {
        val json = sharedPreferences.getString(TIMESLOT_KEY, null)
        val type: Type = object : TypeToken<List<TimeSlotEntity>>() {}.type
        return Gson().fromJson(json, type)
    }

    fun clearSharedPreferences() {
        sharedPreferences.edit()
            .clear()
            .apply()
    }
}