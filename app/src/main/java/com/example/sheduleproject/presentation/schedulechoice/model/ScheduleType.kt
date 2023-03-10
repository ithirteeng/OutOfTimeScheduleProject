package com.example.sheduleproject.presentation.schedulechoice.model

import android.content.Context
import com.example.sheduleproject.R

enum class ScheduleType {
    GROUP,
    EDUCATOR,
    LECTURE_HALL;

    fun getString(context: Context): String =
        when (this) {
            GROUP -> context.getString(R.string.group_text)
            EDUCATOR -> context.getString(R.string.educator)
            else -> context.getString(R.string.lecture_hall)
        }

}