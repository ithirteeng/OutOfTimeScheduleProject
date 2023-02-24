package com.example.sheduleproject.domain.entrance.utils.validator

import com.example.sheduleproject.domain.entrance.utils.ValidationResult

class IdNumberValidator : Validator {

    override fun validate(string: String): ValidationResult {
        return if (string.isEmpty()) {
            ValidationResult.EMPTY_ERROR
        } else if (string.length != 10) {
            ValidationResult.ID_NUMBER_ERROR
        } else {
            ValidationResult.OK
        }
    }

}