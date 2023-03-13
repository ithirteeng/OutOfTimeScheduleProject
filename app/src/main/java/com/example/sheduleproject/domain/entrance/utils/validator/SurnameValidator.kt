package com.example.sheduleproject.domain.entrance.utils.validator

import com.example.sheduleproject.domain.entrance.utils.ValidationResult

class SurnameValidator : Validator {

    private val correctRegex = Regex("[A-ZА-Я][a-zа-я]+")
    private val incorrectRegex = Regex("[^A-ZА-Яa-zа-я]")

    override fun validate(string: String): ValidationResult {
        return if (string.isEmpty()) {
            ValidationResult.EMPTY_ERROR
        } else if (string.matches(correctRegex)) {
            ValidationResult.OK
        } else if (string.contains(incorrectRegex)) {
            ValidationResult.SURNAME_LETTER_ERROR
        } else {
            ValidationResult.SURNAME_CASE_ERROR
        }
    }
}