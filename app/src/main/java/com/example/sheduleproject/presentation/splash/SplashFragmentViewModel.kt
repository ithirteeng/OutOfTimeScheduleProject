package com.example.sheduleproject.presentation.splash

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sheduleproject.data.common.network.interceptor.NoConnectivityException
import com.example.sheduleproject.domain.common.entity.TimeSlotEntity
import com.example.sheduleproject.domain.splash.entity.UserEntity
import com.example.sheduleproject.domain.splash.usecase.*
import com.example.sheduleproject.presentation.common.SingleEventLiveData
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SplashFragmentViewModel(
    application: Application,
    private val getTimeslotsListUseCase: GetTimeslotsListUseCase,
    private val saveTimeSlotsListUseCase: SaveTimeSlotsListUseCase,
    private val checkTokenExistenceUseCase: CheckTokenExistenceUseCase,
    private val checkIfUserWasAuthorizedUseCase: CheckIfUserWasAuthorizedUseCase,
    private val getUserDataUseCase: GetUserDataUseCase
) : AndroidViewModel(application) {

    private val timeSlotsListLiveData = MutableLiveData<List<TimeSlotEntity>>()

    private fun getTimeSlotsList(onErrorAppearance: (errorCode: Int) -> Unit) {
        viewModelScope.launch {
            getTimeslotsListUseCase()
                .onSuccess {
                    timeSlotsListLiveData.value = it
                }
                .onFailure {
                    if (it is HttpException) {
                        onErrorAppearance(it.code())
                    } else if (it is NoConnectivityException) {
                        onErrorAppearance(NoConnectivityException.ERROR_CODE)
                    }
                    Log.e("API_ERROR", "splash get time slots list", it)
                }
        }
    }

    fun getTimeSlotsLiveData(onErrorAppearance: (errorCode: Int) -> Unit): MutableLiveData<List<TimeSlotEntity>> {
        getTimeSlotsList(onErrorAppearance)
        return timeSlotsListLiveData
    }


    private val userDataLiveData =
        SingleEventLiveData<UserEntity>()

    fun getUserData(onErrorAppearance: (errorCode: Int) -> Unit) {
        viewModelScope.launch {
            getUserDataUseCase()
                .onSuccess {
                    userDataLiveData.value = it
                }
                .onFailure {
                    if (it is HttpException) {
                        onErrorAppearance(it.code())
                    } else if (it is NoConnectivityException) {
                        onErrorAppearance(NoConnectivityException.ERROR_CODE)
                    }
                    Log.e("API_ERROR", "get user data splash error", it)
                }
        }
    }


    fun getUserdataLiveData(): MutableLiveData<UserEntity> =
        userDataLiveData


    fun saveTimeSlotsListToLocalStorage(timeSlotsList: List<TimeSlotEntity>) =
        saveTimeSlotsListUseCase(timeSlotsList)

    fun getTokenInfoLiveData(): MutableLiveData<Boolean> =
        MutableLiveData(checkTokenExistenceUseCase())

    fun getUserAuthorizationFlagLiveData(): MutableLiveData<Boolean> =
        MutableLiveData(checkIfUserWasAuthorizedUseCase())
}