package com.example.sheduleproject.domain.entrance.utils.validator

import com.example.sheduleproject.domain.entrance.utils.ValidationResult

interface Validator {
    fun validate(string: String): ValidationResult
}