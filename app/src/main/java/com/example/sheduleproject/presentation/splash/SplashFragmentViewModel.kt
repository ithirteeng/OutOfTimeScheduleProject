package com.example.sheduleproject.presentation.splash

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sheduleproject.domain.common.entity.TimeSlotEntity
import com.example.sheduleproject.domain.splash.usecase.*
import kotlinx.coroutines.launch

class SplashFragmentViewModel(
    application: Application,
    private val getTimeslotsListUseCase: GetTimeslotsListUseCase,
    private val saveTimeSlotsListUseCase: SaveTimeSlotsListUseCase,
    private val checkTokenExistenceUseCase: CheckTokenExistenceUseCase,
    private val checkIfUserWasAuthorizedUseCase: CheckIfUserWasAuthorizedUseCase,
    private val getUserDataUseCase: GetUserDataUseCase
) : AndroidViewModel(application) {

    private val timeSlotsListLiveData = MutableLiveData<List<TimeSlotEntity>?>()
    private fun getTimeSlotsList() {
        viewModelScope.launch {
            getTimeslotsListUseCase()
                .onSuccess {
                    timeSlotsListLiveData.value = it
                }
                .onFailure {
                    Log.e("API_ERROR", "splash", it)
                }
        }
    }

    fun getTimeSlotsLiveData(): MutableLiveData<List<TimeSlotEntity>?> {
        getTimeSlotsList()
        return timeSlotsListLiveData
    }

    fun saveTimeSlotsListToLocalStorage(timeSlotsList: List<TimeSlotEntity>) =
        saveTimeSlotsListUseCase(timeSlotsList)
}