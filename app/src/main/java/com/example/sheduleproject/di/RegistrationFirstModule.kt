package com.example.sheduleproject.di

import com.example.sheduleproject.domain.entrance.registration.usecase.first.ValidateEmailUseCase
import com.example.sheduleproject.domain.entrance.registration.usecase.first.ValidateNameUseCase
import com.example.sheduleproject.domain.entrance.registration.usecase.first.ValidatePatronymicUseCase
import com.example.sheduleproject.domain.entrance.registration.usecase.first.ValidateSurnameUseCase
import com.example.sheduleproject.domain.entrance.utils.validator.EmailValidator
import com.example.sheduleproject.domain.entrance.utils.validator.NameValidator
import com.example.sheduleproject.domain.entrance.utils.validator.PatronymicValidator
import com.example.sheduleproject.domain.entrance.utils.validator.SurnameValidator
import com.example.sheduleproject.presentation.entrance.registration.first.RegistrationFirstViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val registrationFirstModule = module {
    single { NameValidator() }
    single { SurnameValidator() }
    single { PatronymicValidator() }
    single { EmailValidator() }

    factory { ValidateEmailUseCase(validator = get()) }
    factory { ValidateNameUseCase(validator = get()) }
    factory { ValidateSurnameUseCase(validator = get()) }
    factory { ValidatePatronymicUseCase(validator = get()) }

    viewModel {
        RegistrationFirstViewModel(
            application = get(),
            validateEmailUseCase = get(),
            validateNameUseCase = get(),
            validateSurnameUseCase = get(),
            validatePatronymicUseCase = get()
        )
    }
}

