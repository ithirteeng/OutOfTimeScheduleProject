package com.example.sheduleproject.domain.entrance.utils.validator

import com.example.sheduleproject.domain.entrance.utils.ValidationResult

class EmailValidator : Validator {

    private val correctRegex = Regex("^[A-Za-z\\d\\-_.]+@[A-Za-z\\d\\-]+\\.[A-Za-z\\d]+\$")

    override fun validate(string: String): ValidationResult {
        return if (string.isEmpty()) {
            ValidationResult.EMPTY_ERROR
        } else if (!string.matches(correctRegex)) {
            ValidationResult.EMAIL_ERROR
        } else {
            ValidationResult.OK
        }
    }
}

// androidx.appcompat.widget.AppCompatEditText{2bff62b VFED..CL. ........ 0,42-996,194 #7f0900b9 app:id/emailEditText}