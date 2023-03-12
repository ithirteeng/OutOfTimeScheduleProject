package com.example.sheduleproject.presentation.schedule

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sheduleproject.data.common.network.interceptor.NoConnectivityException
import com.example.sheduleproject.domain.common.entity.TimeSlotEntity
import com.example.sheduleproject.domain.schedule.entity.ClassEntity
import com.example.sheduleproject.domain.schedule.usecase.*
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ScheduleFragmentViewModel(
    application: Application,
    private val getTimeSlotListUseCase: GetTimeSlotListUseCase,
    private val getClassesListUseCase: GetClassesListUseCase,
    private val getClassInfoUseCase: GetClassInfoUseCase,
    private val getClassesListByDateFromStorageUseCase: GetClassesListByDateFromStorageUseCase,
    private val saveClassesListToLocalStorageUseCase: SaveClassesListToLocalStorageUseCase,
    private val checkTokenExistenceUseCase: CheckTokenExistenceUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val removeTokenUseCase: RemoveTokenUseCase,
    private val setUserAuthorizationFlagUseCase: SetUserAuthorizationFlagUseCase
) : AndroidViewModel(application) {

    fun getTimeSlotListLiveData(): MutableLiveData<List<TimeSlotEntity>> =
        MutableLiveData(getTimeSlotListUseCase())

    private val classesListLiveData = MutableLiveData<List<ClassEntity>>()

    private fun getAndSetClassesList(
        startDate: String?,
        endDate: String?,
        clusterNumber: String?,
        lectureHallId: String?,
        educatorId: String?,
        dayOfWeek: String?,
        classType: String?,
        onErrorAppearance: (errorCode: Int) -> Unit
    ) {
        viewModelScope.launch {
            getClassesListUseCase(
                startDate = startDate,
                endDate = endDate,
                clusterNumber = clusterNumber,
                educatorId = educatorId,
                lectureHallId = lectureHallId,
                dayOfWeek = dayOfWeek,
                classType = classType
            ).onSuccess {
                classesListLiveData.value = it
            }.onFailure {
                if (it is HttpException) {
                    onErrorAppearance(it.code())
                } else if (it is NoConnectivityException) {
                    onErrorAppearance(NoConnectivityException.ERROR_CODE)
                }
                Log.e("API_ERROR", "schedule_classes_list", it)
            }
        }
    }

    fun getClassesListLiveData(
        startDate: String? = null,
        endDate: String? = null,
        clusterNumber: String? = null,
        educatorId: String? = null,
        lectureHallId: String? = null,
        dayOfWeek: String? = null,
        classType: String? = null,
        onErrorAppearance: (errorCode: Int) -> Unit
    ): MutableLiveData<List<ClassEntity>> {
        getAndSetClassesList(
            startDate = startDate,
            endDate = endDate,
            clusterNumber = clusterNumber,
            educatorId = educatorId,
            lectureHallId = lectureHallId,
            dayOfWeek = dayOfWeek,
            classType = classType,
            onErrorAppearance = onErrorAppearance
        )
        return classesListLiveData
    }

    private val classInfoLiveData = MutableLiveData<ClassEntity>()

    private fun getClassInfo(classId: String, onErrorAppearance: (errorCode: Int) -> Unit) {
        viewModelScope.launch {
            getClassInfoUseCase(classId)
                .onSuccess {
                    classInfoLiveData.value = it
                }
                .onFailure {
                    if (it is HttpException) {
                        onErrorAppearance(it.code())
                    } else if (it is NoConnectivityException) {
                        onErrorAppearance(NoConnectivityException.ERROR_CODE)
                    }
                    Log.e("API_ERROR", "schedule_class_info", it)
                }
        }
    }

    fun getClassesInfoLiveData(
        classId: String,
        onErrorAppearance: (errorCode: Int) -> Unit
    ): MutableLiveData<ClassEntity> {
        getClassInfo(classId, onErrorAppearance)
        return classInfoLiveData
    }

    fun saveClassesListToLocalStorage(classesList: List<ClassEntity>) =
        saveClassesListToLocalStorageUseCase(classesList)


    fun getClassesListByDateFromStorage(date: String): List<ClassEntity> =
        getClassesListByDateFromStorageUseCase(date)


    fun getTokenExistenceResultLiveData(): MutableLiveData<Boolean> =
        MutableLiveData(checkTokenExistenceUseCase())

    fun setUserAuthorizationFlag(authorizationFlag: Boolean) =
        setUserAuthorizationFlagUseCase(authorizationFlag)

    fun removeTokenFromLocalStorage() =
        removeTokenUseCase()


    private val logoutResultsLiveData = MutableLiveData<Boolean>()

    fun makeLogoutRequest(onErrorAppearance: (errorCode: Int) -> Unit) {
        viewModelScope.launch {
            logoutUseCase()
                .onSuccess {
                    logoutResultsLiveData.value = true
                }
                .onFailure {
                    if (it is HttpException) {
                        onErrorAppearance(it.code())
                    } else if (it is NoConnectivityException) {
                        onErrorAppearance(NoConnectivityException.ERROR_CODE)
                    }
                    Log.e("API_ERROR", "schedule_logout", it)
                }
        }
    }

    fun getLogoutResultLiveData(): MutableLiveData<Boolean> = logoutResultsLiveData

}