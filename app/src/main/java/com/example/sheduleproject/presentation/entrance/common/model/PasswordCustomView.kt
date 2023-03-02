package com.example.sheduleproject.presentation.entrance.common.model

import android.content.Context
import android.text.Editable
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.sheduleproject.R
import com.example.sheduleproject.databinding.PasswordCustomViewLayoutBinding

class PasswordCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val layout: View =
        LayoutInflater.from(context).inflate(R.layout.password_custom_view_layout, this)

    private val binding = PasswordCustomViewLayoutBinding.bind(layout)

    private var isPasswordVisible = false

    fun setupFunctions() {
        onEyeButtonClick()
        setInputTextFilter()
    }

    fun text(): Editable = binding.editTextPassword.text

    fun clearText() = binding.editTextPassword.setText("")

    fun setEditTextHint(string: String) {
        binding.editTextPassword.hint = string
    }

    private fun setInputTextFilter() {
        binding.editTextPassword.setEditTextsInputSpaceFilter()
    }

    private fun onEyeButtonClick() {
        with(binding) {
            eyeButton.setOnClickListener {
                if (isPasswordVisible) {
                    editTextPassword.transformationMethod = PasswordTransformationMethod()
                    isPasswordVisible = false
                } else {
                    editTextPassword.transformationMethod = null
                    isPasswordVisible = true
                }
            }
        }

    }
}