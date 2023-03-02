package com.example.sheduleproject.domain.entrance.utils

import android.content.Context
import com.example.sheduleproject.R

enum class ValidationResult {
    EMPTY_ERROR,
    NAME_LETTER_ERROR,
    NAME_CASE_ERROR,
    SURNAME_LETTER_ERROR,
    SURNAME_CASE_ERROR,
    PATRONYMIC_LETTER_ERROR,
    PATRONYMIC_CASE_ERROR,
    PASSWORD_ERROR,
    PASSWORD_LENGTH_ERROR,
    TWO_PASSWORDS_ERROR,
    EMAIL_ERROR,
    ID_NUMBER_ERROR,
    OK;

    fun getErrorString(context: Context) = when (this) {
        OK -> context.getString(R.string.ok_error)
        EMPTY_ERROR -> context.getString(R.string.empty_error)
        EMAIL_ERROR -> context.getString(R.string.email_error)
        NAME_LETTER_ERROR -> context.getString(R.string.name_letters_error)
        NAME_CASE_ERROR -> context.getString(R.string.name_case_error)
        SURNAME_LETTER_ERROR -> context.getString(R.string.surname_letters_error)
        SURNAME_CASE_ERROR -> context.getString(R.string.surname_case_error)
        PATRONYMIC_LETTER_ERROR -> context.getString(R.string.patronymic_letters_error)
        PATRONYMIC_CASE_ERROR -> context.getString(R.string.patronymic_case_error)
        PASSWORD_ERROR -> context.getString(R.string.password_error)
        PASSWORD_LENGTH_ERROR -> context.getString(R.string.password_length_error)
        TWO_PASSWORDS_ERROR -> context.getString(R.string.two_passwords_error)
        else -> context.getString(R.string.id_number_error)
    }
}