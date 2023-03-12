package com.example.sheduleproject.domain.entrance.utils.validator

import com.example.sheduleproject.domain.entrance.utils.ValidationResult

class PasswordValidator : Validator {

    private val correctRegex = Regex("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{6,}")

    override fun validate(string: String): ValidationResult {
        return if (string.isEmpty()) {
            ValidationResult.EMPTY_ERROR
        } else if (string.length < 6) {
            ValidationResult.PASSWORD_LENGTH_ERROR
        } else if (!string.matches(correctRegex)) {
            ValidationResult.PASSWORD_ERROR
        } else {
            ValidationResult.OK
        }
    }
}