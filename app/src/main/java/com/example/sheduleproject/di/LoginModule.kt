package com.example.sheduleproject.di

import com.example.sheduleproject.data.entrance.login.datasource.LocalLoginDatasource
import com.example.sheduleproject.data.entrance.login.datasource.LocalLoginDatasourceImpl
import com.example.sheduleproject.data.entrance.login.datasource.RemoteLoginDatasource
import com.example.sheduleproject.data.entrance.login.datasource.RemoteLoginDatasourceImpl
import com.example.sheduleproject.data.entrance.login.repository.LoginRepositoryImpl
import com.example.sheduleproject.domain.entrance.login.repository.LoginRepository
import com.example.sheduleproject.domain.entrance.login.usecase.*
import com.example.sheduleproject.domain.entrance.utils.validator.EmailValidator
import com.example.sheduleproject.domain.entrance.utils.validator.PasswordValidator
import com.example.sheduleproject.presentation.entrance.login.LoginFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    single { EmailValidator() }
    single { PasswordValidator() }

    factory<RemoteLoginDatasource> { RemoteLoginDatasourceImpl(loginApi = get()) }
    factory<LocalLoginDatasource> { LocalLoginDatasourceImpl(tokenStorage = get()) }

    factory<LoginRepository> {
        LoginRepositoryImpl(
            remoteDatasource = get(),
            localDatasource = get()
        )
    }

    factory { ValidateEmailUseCase(validator = get()) }
    factory { ValidatePasswordUseCase(validator = get()) }
    factory { PostLoginDataUseCase(repository = get()) }
    factory { SaveTokenToLocalStorageUseCase(repository = get()) }
    factory { GetUserDataUseCase(repository = get()) }

    viewModel {
        LoginFragmentViewModel(
            application = get(),
            validateEmailUseCase = get(),
            validatePasswordUseCase = get(),
            postLoginDataUseCase = get(),
            saveTokenToLocalStorageUseCase = get(),
            getUserDataUseCase = get()
        )
    }
}