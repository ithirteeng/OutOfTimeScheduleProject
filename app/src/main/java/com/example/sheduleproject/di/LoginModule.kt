package com.example.sheduleproject.di

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

    factory { ValidateEmailUseCase(validator = get()) }
    factory { ValidatePasswordUseCase(validator = get()) }

    viewModel {
        LoginFragmentViewModel(
            application = get(),
            validateEmailUseCase = get(),
            validatePasswordUseCase = get()
        )
    }
}