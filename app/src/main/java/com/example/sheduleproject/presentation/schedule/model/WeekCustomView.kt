package com.example.sheduleproject.presentation.schedule.model

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.core.view.iterator
import com.example.sheduleproject.R
import com.example.sheduleproject.databinding.WeekCustomViewLayoutBinding
import com.example.sheduleproject.domain.schedule.utils.DateTimeHelper

class WeekCustomView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : ConstraintLayout(context, attributeSet) {

    private val mainView: View =
        LayoutInflater.from(context).inflate(R.layout.week_custom_view_layout, this)
    private val binding = WeekCustomViewLayoutBinding.bind(mainView)

    private val dateTimeHelper = DateTimeHelper(context)

    private var weekDeviation = 0

    private var weekDatesList = arrayListOf<String>()

    private var selectedDate: String = ""

    fun refreshData() {
        weekDatesList = dateTimeHelper.getCalendar(weekDeviation)
        updateDaysData()
    }

    fun getWeekDatesList(): ArrayList<String> = weekDatesList

    fun getSelectedDayName(dayDate: String): String {
        return when (weekDatesList.indexOf(dayDate)) {
            0 -> "Monday"
            1 -> "Tuesday"
            2 -> "Wednesday"
            3 -> "Thursday"
            4 -> "Friday"
            5 -> "Saturday"
            else -> "Sunday"
        }
    }

    fun getSelectedDate(): String = selectedDate

    fun setupWeekCustomView(onDateClick: () -> Unit) {
        setDaysClickListeners(onDateClick)

        refreshData()

        setCurrentDaySelection()
    }

    fun onLeftArrowButtonClick(onClick: () -> Unit) {
        binding.leftArrowButton.setOnClickListener {
            weekDeviation--
            refreshData()
            updateDate()
            onClick()
        }
    }

    fun onRightArrowButtonClick(onClick: () -> Unit) {
        binding.rightArrowButton.setOnClickListener {
            weekDeviation++
            refreshData()
            updateDate()
            onClick()
        }
    }

    private fun setCurrentDaySelection() {
        val currentDayIndex = dateTimeHelper.getCurrentDayIndex()
        val card = binding.linearLayout[currentDayIndex] as DayCustomView

        card.setSelection()
        selectedDate = weekDatesList[currentDayIndex]

        card.performClick()
    }

    private fun getSelectedDay(): DayCustomView {
        var selectedDay = DayCustomView(context)
        for (card in binding.linearLayout) {
            if (card is DayCustomView && card.isDaySelected()) {
                selectedDay = card
                break
            }
        }
        return selectedDay
    }


    private fun updateDate() {
        for (card in binding.linearLayout) {
            if (card is DayCustomView && card.isDaySelected()) {
                selectedDate = card.getDate()
            }
        }
    }

    private fun setDaysClickListeners(onDayClick: () -> Unit) {
        for (card in binding.linearLayout) {
            if (card is DayCustomView) {
                card.setOnClickListener {
                    clearDateSelection()

                    card.setSelection()
                    selectedDate = card.getDate()

                    onDayClick()
                }
            }
        }
    }

    private fun clearDateSelection() {
        for (card in binding.linearLayout) {
            if (card is DayCustomView) {
                card.clearSelection()
            }
        }
    }

    private fun updateDaysData() {
        for (i in 0 until binding.linearLayout.childCount) {
            val view = binding.linearLayout[i]
            if (view is DayCustomView) {
                view.setupDayData(weekDatesList[i], i + 2)
            }
        }
    }


}