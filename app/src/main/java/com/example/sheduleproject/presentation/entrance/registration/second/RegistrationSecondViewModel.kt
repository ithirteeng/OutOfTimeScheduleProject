package com.example.sheduleproject.presentation.entrance.registration.second

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sheduleproject.data.common.network.interceptor.NoConnectivityException
import com.example.sheduleproject.domain.common.entity.TokenEntity
import com.example.sheduleproject.domain.entrance.registration.entity.RegistrationEntity
import com.example.sheduleproject.domain.entrance.registration.usecase.second.*
import com.example.sheduleproject.domain.entrance.utils.ValidationResult
import com.example.sheduleproject.presentation.common.SingleEventLiveData
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegistrationSecondViewModel(
    application: Application,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateTwoPasswordsUseCase: ValidateTwoPasswordsUseCase,
    private val validateIdNumberUseCase: ValidateIdNumberUseCase,
    private val validateClusterUseCase: ValidateClusterUseCase,
    private val postRegistrationDataUseCase: PostRegistrationDataUseCase,
    private val saveTokenToLocalStorageUseCase: SaveTokenToLocalStorageUseCase
) : AndroidViewModel(application) {

    fun getPasswordValidationResultLiveData(string: String): LiveData<ValidationResult> =
        MutableLiveData(validatePasswordUseCase(string))

    fun getTwoPasswordsValidationResultLiveData(string: String): LiveData<ValidationResult> =
        MutableLiveData(validateTwoPasswordsUseCase(string))

    fun getGradleBookNumberValidationResultLiveData(string: String): LiveData<ValidationResult> =
        MutableLiveData(validateIdNumberUseCase(string))


    fun getClusterValidationResultLiveData(string: String): MutableLiveData<ValidationResult> =
        MutableLiveData(validateClusterUseCase(string))


    private val registrationResultLiveData = SingleEventLiveData<TokenEntity?>()

    fun makePostRegistrationDataRequest(
        registrationEntity: RegistrationEntity,
        onErrorAppearance: (errorCode: Int) -> Unit
    ) {
        viewModelScope.launch {
            postRegistrationDataUseCase(registrationEntity)
                .onSuccess {
                    registrationResultLiveData.value = it
                }
                .onFailure {
                    Log.e("API_ERROR", "post registration data", it)
                    if (it is HttpException) {
                        onErrorAppearance(it.code())
                    } else if (it is NoConnectivityException) {
                        onErrorAppearance(NoConnectivityException.ERROR_CODE)
                    }
                    registrationResultLiveData.value = null
                }
        }
    }

    fun getRegistrationResultLiveData(): MutableLiveData<TokenEntity?> = registrationResultLiveData

    fun saveTokenToLocalStorage(tokenEntity: TokenEntity) =
        saveTokenToLocalStorageUseCase(tokenEntity)
}