package com.example.sheduleproject.common

enum class UserType(private val string: String) {
    DEFAULT("Default"),
    EDUCATOR("Educator"),
    STUDENT("Student");

    fun getString(): String = string
}