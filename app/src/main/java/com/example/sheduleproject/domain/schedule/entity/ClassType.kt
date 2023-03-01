package com.example.sheduleproject.domain.schedule.entity

enum class ClassType(private val text: String) {
    PRACTICE("Practice"),
    LECTURE("Lecture"),
    SEMINAR("Seminar"),
    EXAM("Exam"),
    LABORATORY("Laboratory"),
    DEFAULT("Default");

    fun toText(): String = text
}

fun String?.toClassType(): ClassType = when (this) {
    null -> ClassType.DEFAULT
    ClassType.PRACTICE.toText() -> ClassType.PRACTICE
    ClassType.LECTURE.toText() -> ClassType.LECTURE
    ClassType.SEMINAR.toText() -> ClassType.SEMINAR
    ClassType.EXAM.toText() -> ClassType.EXAM
    ClassType.LABORATORY.toText() -> ClassType.LECTURE
    else -> ClassType.DEFAULT
}
