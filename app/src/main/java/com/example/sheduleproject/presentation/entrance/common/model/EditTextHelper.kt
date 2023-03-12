package com.example.sheduleproject.presentation.entrance.common.model

import android.text.InputFilter
import android.widget.EditText

fun EditText.setEditTextsInputSpaceFilter() {
    val filter = InputFilter { source, _, _, _, _, _ ->
        if (source == " " || source.toString().contentEquals("\n")) {
            ""
        } else {
            null
        }
    }
    this.filters = arrayOf(filter)
}