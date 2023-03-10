package com.example.sheduleproject.presentation.schedulechoice

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sheduleproject.data.common.network.interceptor.NoConnectivityException
import com.example.sheduleproject.domain.schedulechoice.entity.ClusterEntity
import com.example.sheduleproject.domain.schedulechoice.entity.EducatorEntity
import com.example.sheduleproject.domain.schedulechoice.entity.LectureHallEntity
import com.example.sheduleproject.domain.schedulechoice.usecase.GetClustersListUseCase
import com.example.sheduleproject.domain.schedulechoice.usecase.GetEducatorsListUseCase
import com.example.sheduleproject.domain.schedulechoice.usecase.GetLectureHallsListUseCase
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ScheduleChoiceFragmentViewModel(
    application: Application,
    private val getClustersListUseCase: GetClustersListUseCase,
    private val getEducatorsListUseCase: GetEducatorsListUseCase,
    private val getLectureHallsListUseCase: GetLectureHallsListUseCase
) : AndroidViewModel(application) {

    private val clustersListLiveData = MutableLiveData<List<ClusterEntity>>()

    fun makeGetClustersListRequest(onErrorAppearance: (errorCode: Int) -> Unit) {
        viewModelScope.launch {
            getClustersListUseCase()
                .onSuccess {
                    clustersListLiveData.value = it
                }
                .onFailure {
                    Log.e("API_ERROR", "get clusters list", it)
                    onErrorAppearance(getErrorCode(it))
                }
        }
    }

    fun getClustersListLiveData(): MutableLiveData<List<ClusterEntity>> =
        clustersListLiveData


    private val educatorsListLiveData = MutableLiveData<List<EducatorEntity>>()

    fun makeGetEducatorsListRequest(onErrorAppearance: (errorCode: Int) -> Unit) {
        viewModelScope.launch {
            getEducatorsListUseCase()
                .onSuccess {
                    educatorsListLiveData.value = it
                }
                .onFailure {
                    Log.e("API_ERROR", "get educators list", it)
                    onErrorAppearance(getErrorCode(it))
                }
        }
    }

    fun getEducatorsListLiveData(): MutableLiveData<List<EducatorEntity>> =
        educatorsListLiveData


    private val lectureHallsListLiveData = MutableLiveData<List<LectureHallEntity>>()

    fun makeGetLectureHallsListRequest(onErrorAppearance: (errorCode: Int) -> Unit) {
        viewModelScope.launch {
            getLectureHallsListUseCase()
                .onSuccess {
                    lectureHallsListLiveData.value = it
                }
                .onFailure {
                    Log.e("API_ERROR", "get lecture halls list", it)
                    onErrorAppearance(getErrorCode(it))
                }
        }
    }

    fun getLectureHallsListLiveData(): MutableLiveData<List<LectureHallEntity>> =
        lectureHallsListLiveData


    private fun getErrorCode(e: Throwable): Int {
        return when (e) {
            is HttpException -> {
                e.code()
            }
            is NoConnectivityException -> {
                NoConnectivityException.ERROR_CODE
            }
            else -> {
                0
            }
        }
    }

}