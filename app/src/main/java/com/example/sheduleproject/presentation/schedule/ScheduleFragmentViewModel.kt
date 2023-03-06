package com.example.sheduleproject.presentation.schedule

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sheduleproject.domain.common.entity.TimeSlotEntity
import com.example.sheduleproject.domain.schedule.entity.ClassEntity
import com.example.sheduleproject.domain.schedule.usecase.*
import kotlinx.coroutines.launch

class ScheduleFragmentViewModel(
    application: Application,
    private val getTimeSlotListUseCase: GetTimeSlotListUseCase,
    private val getClassesListUseCase: GetClassesListUseCase,
    private val getClassInfoUseCase: GetClassInfoUseCase,
    private val getClassesListByDateFromStorageUseCase: GetClassesListByDateFromStorageUseCase,
    private val saveClassesListToLocalStorageUseCase: SaveClassesListToLocalStorageUseCase
) : AndroidViewModel(application) {

    fun getTimeSlotListLiveData(): MutableLiveData<List<TimeSlotEntity>> =
        MutableLiveData(getTimeSlotListUseCase())

    private val classesListLiveData = MutableLiveData<List<ClassEntity>>()

    private fun getAndSetClassesList(
        startDate: String?,
        endDate: String?,
        clusterNumber: String?,
        educatorId: String?,
        dayOfWeek: String?,
        classType: String?,
        onErrorAppeared: () -> Unit
    ) {
        viewModelScope.launch {
            getClassesListUseCase(
                startDate = startDate,
                endDate = endDate,
                clusterNumber = clusterNumber,
                educatorId = educatorId,
                dayOfWeek = dayOfWeek,
                classType = classType
            ).onSuccess {
                classesListLiveData.value = it
            }.onFailure {
                onErrorAppeared()
                Log.e("API_ERROR", "schedule_classes_list", it)
            }
        }
    }

    fun getClassesListLiveData(
        startDate: String? = null,
        endDate: String? = null,
        clusterNumber: String? = null,
        educatorId: String? = null,
        dayOfWeek: String? = null,
        classType: String? = null,
        onErrorAppeared: () -> Unit
    ): MutableLiveData<List<ClassEntity>> {
        getAndSetClassesList(
            startDate = startDate,
            endDate = endDate,
            clusterNumber = clusterNumber,
            educatorId = educatorId,
            dayOfWeek = dayOfWeek,
            classType = classType,
            onErrorAppeared = onErrorAppeared
        )
        return classesListLiveData
    }

    private val classInfoLiveData = MutableLiveData<ClassEntity>()

    private fun getClassInfo(classId: String) {
        viewModelScope.launch {
            getClassInfoUseCase(classId)
                .onSuccess {
                    classInfoLiveData.value = it
                }
                .onFailure {
                    Log.e("API_ERROR", "schedule_class_info", it)
                }
        }
    }

    fun getClassesInfoLiveData(classId: String): MutableLiveData<ClassEntity> {
        getClassInfo(classId)
        return classInfoLiveData
    }

    fun saveClassesListToLocalStorage(classesList: List<ClassEntity>) =
        saveClassesListToLocalStorageUseCase(classesList)


    fun getClassesListByDateFromStorage(date: String): List<ClassEntity> =
        getClassesListByDateFromStorageUseCase(date)


}