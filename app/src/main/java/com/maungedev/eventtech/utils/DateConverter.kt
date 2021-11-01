package com.maungedev.eventtech.utils

import java.text.SimpleDateFormat
import java.util.*

object DateConverter {
    fun convertMillisToString(timeMillis: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeMillis
        val sdf = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
        return sdf.format(calendar.time)
    }

    fun convertMillisToDateMonth(timeMillis: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeMillis
        val sdf = SimpleDateFormat("d MMM", Locale.getDefault())
        return sdf.format(calendar.time)
    }

    fun convertMillisToStringForNotification(timeMillis: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeMillis
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(calendar.time)
    }

    fun convertDateAndTimesToMillis(date: String, time: String): Long {
        val dateArray = date.split("-").toTypedArray()
        val timeArray = time.split(":").toTypedArray()
        val calendar = Calendar.getInstance()
        val hours = Integer.parseInt(timeArray[0])
        val remindersHours = hours - 1

        calendar.set(Calendar.YEAR, Integer.parseInt(dateArray[0]))
        calendar.set(Calendar.MONTH, Integer.parseInt(dateArray[1]) - 1)
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateArray[2]))
        calendar.set(Calendar.HOUR_OF_DAY, remindersHours)
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]))
        calendar.set(Calendar.SECOND, 0)

        return calendar.timeInMillis
    }





 



}