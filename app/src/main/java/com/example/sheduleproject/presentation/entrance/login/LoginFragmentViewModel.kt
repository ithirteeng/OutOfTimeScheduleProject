package com.example.sheduleproject.presentation.entrance.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sheduleproject.data.common.network.interceptor.NoConnectivityException
import com.example.sheduleproject.domain.common.entity.TokenEntity
import com.example.sheduleproject.domain.entrance.login.entity.LoginEntity
import com.example.sheduleproject.domain.entrance.login.usecase.PostLoginDataUseCase
import com.example.sheduleproject.domain.entrance.login.usecase.SaveTokenToLocalStorageUseCase
import com.example.sheduleproject.domain.entrance.login.usecase.ValidateEmailUseCase
import com.example.sheduleproject.domain.entrance.login.usecase.ValidatePasswordUseCase
import com.example.sheduleproject.domain.entrance.utils.ValidationResult
import com.example.sheduleproject.presentation.entrance.common.model.SingleEventLiveData
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginFragmentViewModel(
    application: Application,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val postLoginDataUseCase: PostLoginDataUseCase,
    private val saveTokenToLocalStorageUseCase: SaveTokenToLocalStorageUseCase
) : AndroidViewModel(application) {

    fun getEmailValidationResultLiveData(string: String): LiveData<ValidationResult> =
        MutableLiveData(validateEmailUseCase(string))

    fun getPasswordValidationResultLiveData(string: String): LiveData<ValidationResult> =
        MutableLiveData(validatePasswordUseCase(string))

    fun saveTokenToLocalStorage(tokenEntity: TokenEntity) =
        saveTokenToLocalStorageUseCase(tokenEntity)

    private val tokenLiveData = SingleEventLiveData<TokenEntity>()

    fun postLoginData(loginEntity: LoginEntity, onErrorAppearance: (errorCode: Int) -> Unit) {
        viewModelScope.launch {
            postLoginDataUseCase.invoke(loginEntity)
                .onSuccess {
                    tokenLiveData.value = it
                }
                .onFailure {
                    if (it is HttpException) {
                        onErrorAppearance(it.code())
                    } else if (it is NoConnectivityException) {
                        onErrorAppearance(NoConnectivityException.ERROR_CODE)
                    }
                    Log.e("API_ERROR", "login_error", it)
                }
        }
    }

    fun getTokenLiveData(): MutableLiveData<TokenEntity> = tokenLiveData
}