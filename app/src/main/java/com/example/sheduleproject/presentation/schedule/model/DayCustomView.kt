package com.example.sheduleproject.presentation.schedule.model

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.example.sheduleproject.R
import com.example.sheduleproject.databinding.DayCustomViewLayoutBinding
import com.example.sheduleproject.domain.schedule.utils.DateTimeHelper


class DayCustomView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : RelativeLayout(context, attributeSet) {

    private val binding = DayCustomViewLayoutBinding.inflate(LayoutInflater.from(context), this)

    private val dateTimeHelper = DateTimeHelper(context)

    private var isDaySelected = false

    fun setupDayData(date: String, dayIndex: Int) {
        this.date = date
        this.dayIndex = dayIndex

        setDayName()
        setDayNumber()
    }

    private var date: String = ""
    private var dayIndex: Int = 0

    fun isDaySelected(): Boolean = isDaySelected

    fun getDate(): String = this.date

    fun setOnClickListener(onClick: () -> Unit) {
        binding.cardView.setOnClickListener {
            onClick()
        }
    }

    fun setSelection() {
        isDaySelected = true
        colorizeInBlack()
    }

    fun clearSelection() {
        isDaySelected = false
        colorizeInWhite()
    }

    private fun setDayName() {
        binding.dayNameTextView.text = dateTimeHelper.mapDayCardMonthToCorrectView(dayIndex)
    }

    private fun setDayNumber() {
        binding.dayNumberTextView.text = dateTimeHelper.mapDayCardNumberToCorrectForm(date)
    }

    private fun colorizeInBlack() {
        binding.cardView.setCardBackgroundColor(context.getColor(R.color.gray_black))
        binding.dayNameTextView.setTextColor(context.getColor(R.color.white))
        binding.dayNumberTextView.setTextColor(context.getColor(R.color.white))
    }

    private fun colorizeInWhite() {
        binding.cardView.setCardBackgroundColor(context.getColor(R.color.white))
        binding.dayNameTextView.setTextColor(context.getColor(R.color.gray_black))
        binding.dayNumberTextView.setTextColor(context.getColor(R.color.gray_black))
    }

}