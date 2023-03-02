package com.example.sheduleproject.presentation.entrance.registration.second

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sheduleproject.domain.entrance.registration.usecase.second.ValidateIdNumberUseCase
import com.example.sheduleproject.domain.entrance.registration.usecase.second.ValidatePasswordUseCase
import com.example.sheduleproject.domain.entrance.registration.usecase.second.ValidateTwoPasswordsUseCase
import com.example.sheduleproject.domain.entrance.utils.ValidationResult

class RegistrationSecondViewModel(
    application: Application,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateTwoPasswordsUseCase: ValidateTwoPasswordsUseCase,
    private val validateIdNumberUseCase: ValidateIdNumberUseCase
) : AndroidViewModel(application) {

    fun getPasswordValidationResultLiveData(string: String): LiveData<ValidationResult> =
        MutableLiveData(validatePasswordUseCase(string))

    fun getTwoPasswordsValidationResultLiveData(string: String): LiveData<ValidationResult> =
        MutableLiveData(validateTwoPasswordsUseCase(string))

    fun getIdNumberValidationResultLiveData(string: String): LiveData<ValidationResult> =
        MutableLiveData(validateIdNumberUseCase(string))
}