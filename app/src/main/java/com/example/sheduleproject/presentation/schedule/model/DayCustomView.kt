package com.example.sheduleproject.presentation.schedule.model

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.example.sheduleproject.R
import com.example.sheduleproject.databinding.DayCustomViewLayoutBinding


class DayCustomView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : RelativeLayout(context, attributeSet) {

    private val binding = DayCustomViewLayoutBinding.inflate(LayoutInflater.from(context), this)

    fun setOnClickListener(onClick: () -> Unit) {
        binding.cardView.setOnClickListener {
            colorizeInBlack()
            onClick()
        }
    }

    fun setDayName(dayName: String) {
        binding.dayNameTextView.text = dayName
    }

    fun setDayNumber(dayNumber: Int) {
        binding.dayNumberTextView.text = dayNumber.toString()
    }

    private fun colorizeInBlack() {
        binding.cardView.setCardBackgroundColor(context.getColor(R.color.gray_black))
        binding.dayNameTextView.setTextColor(context.getColor(R.color.white))
        binding.dayNumberTextView.setTextColor(context.getColor(R.color.white))
    }

    fun colorizeInWhite() {
        binding.cardView.setCardBackgroundColor(context.getColor(R.color.white))
        binding.dayNameTextView.setTextColor(context.getColor(R.color.gray_black))
        binding.dayNumberTextView.setTextColor(context.getColor(R.color.gray_black))
    }

}