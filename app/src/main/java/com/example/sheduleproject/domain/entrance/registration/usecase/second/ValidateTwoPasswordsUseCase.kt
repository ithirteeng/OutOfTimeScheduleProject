package com.example.sheduleproject.domain.entrance.registration.usecase.second

import com.example.sheduleproject.domain.entrance.utils.ValidationResult
import com.example.sheduleproject.domain.entrance.utils.validator.TwoPasswordsValidator

class ValidateTwoPasswordsUseCase(private val validator: TwoPasswordsValidator) {
    operator fun invoke(string: String): ValidationResult =
        validator.validate(string)
}