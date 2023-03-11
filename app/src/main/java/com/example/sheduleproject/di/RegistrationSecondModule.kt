package com.example.sheduleproject.di

import com.example.sheduleproject.data.entrance.registration.datasource.LocalRegistrationDatasource
import com.example.sheduleproject.data.entrance.registration.datasource.LocalRegistrationDatasourceImpl
import com.example.sheduleproject.data.entrance.registration.datasource.RemoteRegistrationDatasource
import com.example.sheduleproject.data.entrance.registration.datasource.RemoteRegistrationDatasourceImpl
import com.example.sheduleproject.data.entrance.registration.repository.RegistrationRepositoryImpl
import com.example.sheduleproject.domain.entrance.registration.repository.RegistrationRepository
import com.example.sheduleproject.domain.entrance.registration.usecase.second.*
import com.example.sheduleproject.domain.entrance.utils.validator.ClusterValidator
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
    single { ClusterValidator() }

    factory<RemoteRegistrationDatasource> { RemoteRegistrationDatasourceImpl(api = get()) }
    factory<LocalRegistrationDatasource> { LocalRegistrationDatasourceImpl(storage = get()) }

    factory<RegistrationRepository> {
        RegistrationRepositoryImpl(
            remoteDatasource = get(),
            localDatasource = get()
        )
    }

    factory { ValidatePasswordUseCase(validator = get()) }
    factory { ValidateClusterUseCase(validator = get()) }
    factory { ValidateTwoPasswordsUseCase(validator = get()) }
    factory { ValidateIdNumberUseCase(validator = get()) }
    factory { PostRegistrationDataUseCase(repository = get()) }
    factory { SaveTokenToLocalStorageUseCase(repository = get()) }

    viewModel {
        RegistrationSecondViewModel(
            application = get(),
            validatePasswordUseCase = get(),
            validateTwoPasswordsUseCase = get(),
            validateIdNumberUseCase = get(),
            validateClusterUseCase = get(),
            postRegistrationDataUseCase = get(),
            saveTokenToLocalStorageUseCase = get()
        )
    }
}