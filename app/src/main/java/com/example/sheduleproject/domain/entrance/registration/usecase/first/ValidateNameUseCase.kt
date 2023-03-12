package com.example.sheduleproject.domain.entrance.registration.usecase.first

import com.example.sheduleproject.domain.entrance.utils.ValidationResult
import com.example.sheduleproject.domain.entrance.utils.validator.NameValidator

class ValidateNameUseCase(private val validator: NameValidator) {
    operator fun invoke(string: String): ValidationResult =
        validator.validate(string)
}