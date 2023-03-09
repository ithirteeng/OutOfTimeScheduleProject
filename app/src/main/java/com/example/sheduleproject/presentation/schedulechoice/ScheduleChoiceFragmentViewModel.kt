package com.example.sheduleproject.presentation.schedulechoice

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.sheduleproject.domain.schedulechoice.usecase.GetClustersListUseCase
import com.example.sheduleproject.domain.schedulechoice.usecase.GetEducatorsListUseCase
import com.example.sheduleproject.domain.schedulechoice.usecase.GetLectureHallsListUseCase

class ScheduleChoiceFragmentViewModel(
    application: Application,
    private val getClustersListUseCase: GetClustersListUseCase,
    private val getEducatorsListUseCase: GetEducatorsListUseCase,
    private val getLectureHallsListUseCase: GetLectureHallsListUseCase
) : AndroidViewModel(application) {

}