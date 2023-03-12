package com.example.sheduleproject.domain.entrance.registration.usecase.second

import com.example.sheduleproject.domain.entrance.utils.validator.IdNumberValidator

class ValidateIdNumberUseCase(private val validator: IdNumberValidator) {
    operator fun invoke(string: String) =
        validator.validate(string)
}