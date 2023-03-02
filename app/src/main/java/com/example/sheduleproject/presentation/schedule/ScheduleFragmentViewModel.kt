package com.example.sheduleproject.presentation.schedule

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.sheduleproject.domain.common.entity.TimeSlotEntity
import com.example.sheduleproject.domain.schedule.usecase.GetTimeSlotListUseCase

class ScheduleFragmentViewModel(
    application: Application,
    private val getTimeSlotListUseCase: GetTimeSlotListUseCase
) : AndroidViewModel(application) {

    fun getTimeSlotListLiveData(): MutableLiveData<List<TimeSlotEntity>> =
        MutableLiveData(getTimeSlotListUseCase())
}