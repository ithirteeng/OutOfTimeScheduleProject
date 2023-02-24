package com.example.sheduleproject.di

import com.example.sheduleproject.domain.entrance.registration.usecase.second.ValidateIdNumberUseCase
import com.example.sheduleproject.domain.entrance.registration.usecase.second.ValidatePasswordUseCase
import com.example.sheduleproject.domain.entrance.registration.usecase.second.ValidateTwoPasswordsUseCase
import com.example.sheduleproject.domain.entrance.utils.validator.IdNumberValidator
import com.example.sheduleproject.domain.entrance.utils.validator.PasswordValidator
import com.example.sheduleproject.domain.entrance.utils.validator.TwoPasswordsValidator
import com.example.sheduleproject.presentation.entrance.registration.second.RegistrationSecondViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val registrationSecondModule = module {
    single { PasswordValidator() }
    single { TwoPasswordsValidator() }
    single { IdNumberValidator() }

    factory { ValidatePasswordUseCase(validator = get()) }
    factory { ValidateTwoPasswordsUseCase(validator = get()) }
    factory { ValidateIdNumberUseCase(validator = get()) }

    viewModel {
        RegistrationSecondViewModel(
            application = get(),
            validatePasswordUseCase = get(),
            validateTwoPasswordsUseCase = get(),
            validateIdNumberUseCase = get()
        )
    }
}