package com.example.sheduleproject.presentation.entrance.registration.first

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sheduleproject.domain.entrance.registration.usecase.first.ValidateEmailUseCase
import com.example.sheduleproject.domain.entrance.registration.usecase.first.ValidateNameUseCase
import com.example.sheduleproject.domain.entrance.registration.usecase.first.ValidatePatronymicUseCase
import com.example.sheduleproject.domain.entrance.registration.usecase.first.ValidateSurnameUseCase
import com.example.sheduleproject.domain.entrance.utils.ValidationResult

class RegistrationFirstViewModel(
    application: Application,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validateNameUseCase: ValidateNameUseCase,
    private val validateSurnameUseCase: ValidateSurnameUseCase,
    private val validatePatronymicUseCase: ValidatePatronymicUseCase,
) : AndroidViewModel(application) {

    fun getEmailValidationResultLiveData(string: String): LiveData<ValidationResult> =
        MutableLiveData(validateEmailUseCase(string))

    fun getNameValidationResultLiveData(string: String): LiveData<ValidationResult> =
        MutableLiveData(validateNameUseCase(string))

    fun getSurnameValidationResultLiveData(string: String): LiveData<ValidationResult> =
        MutableLiveData(validateSurnameUseCase(string))

    fun getPatronymicValidationResultLiveData(string: String): LiveData<ValidationResult> =
        MutableLiveData(validatePatronymicUseCase(string))

}