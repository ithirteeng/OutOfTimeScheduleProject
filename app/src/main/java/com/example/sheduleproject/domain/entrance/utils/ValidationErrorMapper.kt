package com.example.sheduleproject.domain.entrance.utils

import android.content.Context
import com.example.sheduleproject.R

fun ValidationResult.toStringError(context: Context): String {
    return when (this) {
        ValidationResult.OK -> context.getString(R.string.ok_error)
        ValidationResult.EMPTY_ERROR -> context.getString(R.string.empty_error)
        ValidationResult.EMAIL_ERROR -> context.getString(R.string.email_error)
        ValidationResult.NAME_LETTER_ERROR -> context.getString(R.string.name_letters_error)
        ValidationResult.NAME_CASE_ERROR -> context.getString(R.string.name_case_error)
        ValidationResult.SURNAME_LETTER_ERROR -> context.getString(R.string.surname_letters_error)
        ValidationResult.SURNAME_CASE_ERROR -> context.getString(R.string.surname_case_error)
        ValidationResult.PATRONYMIC_LETTER_ERROR -> context.getString(R.string.patronymic_letters_error)
        ValidationResult.PATRONYMIC_CASE_ERROR -> context.getString(R.string.patronymic_case_error)
        ValidationResult.PASSWORD_ERROR -> context.getString(R.string.password_error)
        ValidationResult.PASSWORD_LENGTH_ERROR -> context.getString(R.string.password_length_error)
        ValidationResult.TWO_PASSWORDS_ERROR -> context.getString(R.string.two_passwords_error)
        else -> context.getString(R.string.id_number_error)
    }
}
