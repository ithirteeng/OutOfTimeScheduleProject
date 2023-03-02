package com.example.sheduleproject.domain.entrance.registration.usecase.first

import com.example.sheduleproject.domain.entrance.utils.ValidationResult
import com.example.sheduleproject.domain.entrance.utils.validator.EmailValidator

class ValidateEmailUseCase(private val validator: EmailValidator) {
    operator fun invoke(string: String): ValidationResult =
        validator.validate(string)
}