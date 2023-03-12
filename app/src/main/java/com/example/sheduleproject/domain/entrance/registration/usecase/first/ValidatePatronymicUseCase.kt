package com.example.sheduleproject.domain.entrance.registration.usecase.first

import com.example.sheduleproject.domain.entrance.utils.ValidationResult
import com.example.sheduleproject.domain.entrance.utils.validator.PatronymicValidator

class ValidatePatronymicUseCase(private val validator: PatronymicValidator) {
    operator fun invoke(string: String): ValidationResult =
        validator.validate(string)
}