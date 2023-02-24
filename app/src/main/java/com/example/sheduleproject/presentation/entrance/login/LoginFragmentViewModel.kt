package com.example.sheduleproject.presentation.entrance.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sheduleproject.domain.entrance.login.usecase.ValidateEmailUseCase
import com.example.sheduleproject.domain.entrance.login.usecase.ValidatePasswordUseCase
import com.example.sheduleproject.domain.entrance.utils.ValidationResult

class LoginFragmentViewModel(
    application: Application,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) : AndroidViewModel(application) {

    fun getEmailValidationResultLiveData(string: String): LiveData<ValidationResult> =
        MutableLiveData(validateEmailUseCase(string))

    fun getPasswordValidationResultLiveData(string: String): LiveData<ValidationResult> =
        MutableLiveData(validatePasswordUseCase(string))
}