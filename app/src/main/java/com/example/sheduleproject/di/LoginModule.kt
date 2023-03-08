package com.example.sheduleproject.di

import com.example.sheduleproject.data.entrance.login.datasource.LoginDatasource
import com.example.sheduleproject.data.entrance.login.datasource.LoginDatasourceImpl
import com.example.sheduleproject.data.entrance.login.repository.LoginRepositoryImpl
import com.example.sheduleproject.domain.entrance.login.repository.LoginRepository
import com.example.sheduleproject.domain.entrance.login.usecase.PostLoginDataUseCase
import com.example.sheduleproject.domain.entrance.login.usecase.SaveTokenToLocalStorageUseCase
import com.example.sheduleproject.domain.entrance.login.usecase.ValidateEmailUseCase
import com.example.sheduleproject.domain.entrance.login.usecase.ValidatePasswordUseCase
import com.example.sheduleproject.domain.entrance.utils.validator.EmailValidator
import com.example.sheduleproject.domain.entrance.utils.validator.PasswordValidator
import com.example.sheduleproject.presentation.entrance.login.LoginFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    single { EmailValidator() }
    single { PasswordValidator() }

    factory<LoginDatasource> { LoginDatasourceImpl(loginApi = get()) }
    factory<LoginRepository> {
        LoginRepositoryImpl(
            datasource = get(),
            tokenStorage = get()
        )
    }

    factory { ValidateEmailUseCase(validator = get()) }
    factory { ValidatePasswordUseCase(validator = get()) }
    factory { PostLoginDataUseCase(repository = get()) }
    factory { SaveTokenToLocalStorageUseCase(repository = get()) }

    viewModel {
        LoginFragmentViewModel(
            application = get(),
            validateEmailUseCase = get(),
            validatePasswordUseCase = get(),
            postLoginDataUseCase = get(),
            saveTokenToLocalStorageUseCase = get()
        )
    }
}