package com.example.sheduleproject.data.schedule.storage

import android.content.Context
import com.example.sheduleproject.data.schedule.model.ClassModel
import com.example.sheduleproject.domain.schedule.utils.DateTimeHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ClassesStorage(private val context: Context) {
    companion object {
        const val CLASS_KEY = "class key"
        const val CLASSES_STORAGE_NAME = "classes storage name"
    }

    private val sharedPreferences =
        context.getSharedPreferences(CLASSES_STORAGE_NAME, Context.MODE_PRIVATE)

    fun saveClassesListToLocalStorage(classesList: List<ClassModel>) {
        val json = Gson().toJson(classesList)
        sharedPreferences.edit()
            .putString(CLASS_KEY, json)
            .apply()
    }

    fun getClassesListByDate(date: String): List<ClassModel> {
        val json = sharedPreferences.getString(CLASS_KEY, null)
        val type: Type = object : TypeToken<List<ClassModel>>() {}.type
        val allClassesList = Gson().fromJson(json, type) as List<ClassModel>

        return setupClassesListByDate(date, allClassesList)
    }

    private fun setupClassesListByDate(
        date: String,
        classesList: List<ClassModel>
    ): List<ClassModel> {
        val dateTimeHelper = DateTimeHelper(context = context)
        val result = arrayListOf<ClassModel>()
        for (classModel in classesList) {
            if (dateTimeHelper.compareDates(classModel.date, date)) {
                result.add(classModel)
            }
        }
        return result.toList()
    }

}