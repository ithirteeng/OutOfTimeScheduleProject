package com.example.sheduleproject.domain.schedule.utils

import android.content.Context
import com.example.sheduleproject.R
import java.time.OffsetDateTime
import java.time.ZoneId
import java.util.*

class DateTimeHelper(val context: Context) {
    fun getCurrentDayOfWeek(): String =
        convertDays(Calendar.getInstance().get(Calendar.DAY_OF_WEEK))


    fun getCurrentDayOfMonth(): Int =
        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)


    fun getCurrentMonth(): String =
        convertMonths(Calendar.getInstance().get(Calendar.MONTH))


    fun getCurrentYear(): Int =
        Calendar.getInstance().get(Calendar.YEAR)


    private fun convertMonths(monthIndex: Int): String {
        return when (monthIndex) {
            Calendar.JANUARY -> context.getString(R.string.january)
            Calendar.FEBRUARY -> context.getString(R.string.february)
            Calendar.MARCH -> context.getString(R.string.march)
            Calendar.APRIL -> context.getString(R.string.april)
            Calendar.MAY -> context.getString(R.string.may)
            Calendar.JUNE -> context.getString(R.string.june)
            Calendar.JULY -> context.getString(R.string.july)
            Calendar.AUGUST -> context.getString(R.string.august)
            Calendar.SEPTEMBER -> context.getString(R.string.september)
            Calendar.OCTOBER -> context.getString(R.string.october)
            Calendar.NOVEMBER -> context.getString(R.string.november)
            else -> context.getString(R.string.december)
        }
    }

    private fun convertDays(dayIndex: Int): String {
        return when (dayIndex) {
            Calendar.MONDAY -> context.getString(R.string.monday_short)
            Calendar.TUESDAY -> context.getString(R.string.tuesday_short)
            Calendar.WEDNESDAY -> context.getString(R.string.wednesday_short)
            Calendar.THURSDAY -> context.getString(R.string.thursday_short)
            Calendar.FRIDAY -> context.getString(R.string.friday_short)
            Calendar.SATURDAY -> context.getString(R.string.saturday_short)
            else -> context.getString(R.string.sunday_short)
        }
    }

    fun getCorrectClassesTime(dateString: String): String {
        val offsetDateTime = OffsetDateTime.parse(dateString)
        val zonedDateTime = offsetDateTime.atZoneSameInstant(ZoneId.systemDefault())
        val localDateTime = zonedDateTime.toLocalDateTime()
        val localDateTimeString = localDateTime.toString()
        return localDateTimeString.split('T')[1]
    }

}
