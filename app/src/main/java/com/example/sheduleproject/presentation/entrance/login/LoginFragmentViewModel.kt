package com.example.sheduleproject.presentation.entrance.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sheduleproject.data.common.network.interceptor.NoConnectivityException
import com.example.sheduleproject.domain.entrance.login.entity.LoginEntity
import com.example.sheduleproject.domain.entrance.login.entity.UserEntity
import com.example.sheduleproject.domain.entrance.login.usecase.*
import com.example.sheduleproject.domain.entrance.utils.ValidationResult
import com.example.sheduleproject.domain.token.entity.TokenEntity
import com.example.sheduleproject.presentation.common.SingleEventLiveData
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginFragmentViewModel(
    application: Application,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val postLoginDataUseCase: PostLoginDataUseCase,
    private val saveTokenToLocalStorageUseCase: SaveTokenToLocalStorageUseCase,
    private val getUserDataUseCase: GetUserDataUseCase,
    private val setUserAuthorizationFlagUseCase: SetUserAuthorizationFlagUseCase
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
                    Log.e("API_ERROR", "post login error", it)
                }
        }
    }

    fun getTokenLiveData(): MutableLiveData<TokenEntity> = tokenLiveData


    private val userDataLiveData = SingleEventLiveData<UserEntity>()

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
                    Log.e("API_ERROR", "get user data login error", it)
                }
        }
    }

    fun getUserdataLiveData(): MutableLiveData<UserEntity> = userDataLiveData


    fun setIfUserWasAuthorizedFlag(authorizationFlag: Boolean) {
        setUserAuthorizationFlagUseCase(authorizationFlag)
    }


}