package com.example.sheduleproject.domain.entrance.utils.validator

import com.example.sheduleproject.domain.entrance.utils.ValidationResult

class PatronymicValidator : Validator {

    private val correctRegex = Regex("[A-Z][a-z]+")
    private val incorrectRegex = Regex("[^A-Za-z]")

    override fun validate(string: String): ValidationResult {
        return if (string.isEmpty()) {
            ValidationResult.EMPTY_ERROR
        } else if (string.matches(correctRegex)) {
            ValidationResult.OK
        } else if (string.contains(incorrectRegex)) {
            ValidationResult.PATRONYMIC_LETTER_ERROR
        } else {
            ValidationResult.PATRONYMIC_CASE_ERROR
        }
    }
}