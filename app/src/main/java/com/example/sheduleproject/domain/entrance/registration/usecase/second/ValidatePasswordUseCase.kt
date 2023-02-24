package com.example.sheduleproject.domain.entrance.registration.usecase.second

import com.example.sheduleproject.domain.entrance.utils.ValidationResult
import com.example.sheduleproject.domain.entrance.utils.validator.PasswordValidator

class ValidatePasswordUseCase(private val validator: PasswordValidator) {
    operator fun invoke(string: String): ValidationResult =
        validator.validate(string)
}