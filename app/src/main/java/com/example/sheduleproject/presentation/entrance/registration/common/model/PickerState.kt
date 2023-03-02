package com.example.sheduleproject.presentation.entrance.registration.common.model

class PickerState {
    private var student = false
    private var teacher = false

    fun changeStudentState() {
        student = !student
    }

    fun changeTeacherState() {
        teacher = !teacher
    }

    fun setStudentState(state: Boolean) {
        student = state
    }

    fun setTeacherState(state: Boolean) {
        teacher = state
    }

    fun getStudentState(): Boolean = student

    fun getTeacherState(): Boolean = teacher

}