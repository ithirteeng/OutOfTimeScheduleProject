package com.example.sheduleproject.presentation.entrance.registration.common.model

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.sheduleproject.R
import com.example.sheduleproject.common.UserType
import com.example.sheduleproject.databinding.UserTypeLayoutBinding

class UserTypePickerCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs) {
    private val pickerView: View =
        LayoutInflater.from(context).inflate(R.layout.user_type_layout, this)
    private val binding = UserTypeLayoutBinding.bind(pickerView)

    private val buttonsChecker = PickerState()

    fun checkIsPickerInvolved(): Boolean {
        return isStudentButtonChosen() || isTeacherButtonChosen()
    }

    private fun isStudentButtonChosen(): Boolean = buttonsChecker.getStudentState()

    private fun isTeacherButtonChosen(): Boolean = buttonsChecker.getTeacherState()

    fun onPickerButtonsClick(changeListener: () -> Unit) {
        onStudentButtonClick(changeListener)
        onTeacherButtonClick(changeListener)
    }

    fun getCorrectMeaningOfUserType(): UserType {
        return if (isStudentButtonChosen()) {
            UserType.STUDENT
        } else {
            UserType.TEACHER
        }
    }

    fun setCorrectType(gender: Int) {
        if (gender == 1) {
            buttonsChecker.setStudentState(true)
            buttonsChecker.setTeacherState(false)
            changeStudentButtonBackground(buttonsChecker.getStudentState())
            changeTeacherButtonBackground(buttonsChecker.getTeacherState())
        } else {
            buttonsChecker.setTeacherState(true)
            buttonsChecker.setStudentState(false)
            changeTeacherButtonBackground(buttonsChecker.getTeacherState())
            changeStudentButtonBackground(buttonsChecker.getStudentState())
        }
    }

    private fun onStudentButtonClick(changeListener: () -> Unit) {
        binding.studentButton.setOnClickListener {
            if (buttonsChecker.getTeacherState()) {
                buttonsChecker.changeTeacherState()
                changeTeacherButtonBackground(buttonsChecker.getTeacherState())
            }
            buttonsChecker.changeStudentState()
            changeStudentButtonBackground(buttonsChecker.getStudentState())
            changeListener()
        }
    }

    private fun onTeacherButtonClick(changeListener: () -> Unit) {
        binding.teacherButton.setOnClickListener {
            if (buttonsChecker.getStudentState()) {
                buttonsChecker.changeStudentState()
                changeStudentButtonBackground(buttonsChecker.getStudentState())
            }
            buttonsChecker.changeTeacherState()
            changeTeacherButtonBackground(buttonsChecker.getTeacherState())
            changeListener()

        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun changeTeacherButtonBackground(state: Boolean) {
        if (state) {
            binding.teacherButton.background = resources.getDrawable(
                R.drawable.user_type_picker_filled_right_part,
                context.theme
            )
        } else {
            binding.teacherButton.background = resources.getDrawable(
                R.drawable.user_type_picker_empty_right_part,
                context.theme
            )
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun changeStudentButtonBackground(state: Boolean) {
        if (state) {
            binding.studentButton.background = resources.getDrawable(
                R.drawable.user_type_picker_filled_left_part,
                context.theme
            )
        } else {
            binding.studentButton.background = resources.getDrawable(
                R.drawable.user_type_picker_empty_left_part,
                context.theme
            )
        }
    }


}