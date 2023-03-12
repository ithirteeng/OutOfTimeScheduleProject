package com.example.sheduleproject.domain.schedule.utils

import android.annotation.SuppressLint
import android.content.Context
import com.example.sheduleproject.R
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.ZoneId
import java.util.*

class DateTimeHelper(val context: Context) {

    private var fullCalendar = Calendar.getInstance()

    @SuppressLint("SimpleDateFormat")
    fun setDate(date: String) {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        fullCalendar.time = simpleDateFormat.parse(date)!!
    }

    fun getCurrentDayOfWeek(): String =
        convertDays(fullCalendar.get(Calendar.DAY_OF_WEEK))


    fun getCurrentDayOfMonth(): Int =
        fullCalendar.get(Calendar.DAY_OF_MONTH)


    fun getCurrentMonth(): String =
        convertMonths(fullCalendar.get(Calendar.MONTH))


    fun getCurrentYear(): Int =
        fullCalendar.get(Calendar.YEAR)


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

    @SuppressLint("SimpleDateFormat")
    fun getCalendar(weeksDeviation: Int): ArrayList<String> {

        val calendar = Calendar.getInstance()
        calendar.firstDayOfWeek = Calendar.MONDAY
        calendar.add(Calendar.WEEK_OF_MONTH, weeksDeviation)
        calendar[Calendar.DAY_OF_WEEK] = calendar.firstDayOfWeek
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

        val days: ArrayList<String> = arrayListOf()

        for (i in 0..6) {
            days.add(simpleDateFormat.format(calendar.time))
            calendar.add(Calendar.DAY_OF_WEEK, 1)
        }

        return days
    }

    fun compareDates(fullDate: String, date: String): Boolean {
        return fullDate.split("T")[0] == date
    }

    fun getCurrentDayIndex(): Int = when (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
        Calendar.MONDAY -> 0
        Calendar.TUESDAY -> 1
        Calendar.WEDNESDAY -> 2
        Calendar.THURSDAY -> 3
        Calendar.FRIDAY -> 4
        Calendar.SATURDAY -> 5
        else -> 6
    }

    fun mapDayCardMonthToCorrectView(dayIndex: Int): String {
        return convertDays(dayIndex)
    }

    fun mapDayCardNumberToCorrectForm(date: String): String {
        return date.split('-')[2]
    }

}
