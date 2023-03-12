package com.example.sheduleproject.domain.entrance.registration.usecase.first

import com.example.sheduleproject.domain.entrance.utils.ValidationResult
import com.example.sheduleproject.domain.entrance.utils.validator.SurnameValidator

class ValidateSurnameUseCase(private val validator: SurnameValidator) {
    operator fun invoke(string: String): ValidationResult =
        validator.validate(string)
}