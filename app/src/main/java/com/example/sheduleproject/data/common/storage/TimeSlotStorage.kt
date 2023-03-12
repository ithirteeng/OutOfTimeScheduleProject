package com.example.sheduleproject.data.common.storage

import android.content.Context
import com.example.sheduleproject.data.common.model.TimeSlotModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class TimeSlotStorage(context: Context) {

    companion object {
        const val TIMESLOT_KEY = "timeslot key"
        const val TIME_STORAGE_NAME = "time storage name"
    }

    private val sharedPreferences =
        context.getSharedPreferences(TIME_STORAGE_NAME, Context.MODE_PRIVATE)

    fun saveTimeSlotsListToLocalStorage(timeSlotList: List<TimeSlotModel>) {
        val json = Gson().toJson(timeSlotList)
        sharedPreferences.edit()
            .putString(TIMESLOT_KEY, json)
            .apply()
    }

    fun getTimeSlotListFromLocalStorage(): List<TimeSlotModel> {
        val json = sharedPreferences.getString(TIMESLOT_KEY, null)
        val type: Type = object : TypeToken<List<TimeSlotModel>>() {}.type
        return Gson().fromJson(json, type)
    }
}