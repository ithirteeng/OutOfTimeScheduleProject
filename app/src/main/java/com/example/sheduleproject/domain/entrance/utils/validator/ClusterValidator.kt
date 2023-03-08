package com.example.sheduleproject.domain.entrance.utils.validator

import com.example.sheduleproject.domain.entrance.utils.ValidationResult

class ClusterValidator : Validator {
    override fun validate(string: String): ValidationResult {
        return if (string.isEmpty()) {
            ValidationResult.EMPTY_ERROR
        } else {
            ValidationResult.OK
        }
    }
}