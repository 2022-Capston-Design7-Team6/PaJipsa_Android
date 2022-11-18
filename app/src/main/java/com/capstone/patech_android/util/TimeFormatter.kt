package com.capstone.patech_android.util

import java.text.SimpleDateFormat
import java.util.*

fun timeFormatToCalender(inputDate: String): Calendar? {
    val format = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX"
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.time = sdf.parse(inputDate) ?: return null
    calendar.timeZone = TimeZone.getTimeZone("Etc/UTC")
    return calendar
}

fun timeFormatToPreviewDate(inputDate: String): String {
    val format = "yyyy-MM-dd"
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.time = sdf.parse(inputDate) ?: return ""
    calendar.timeZone = TimeZone.getTimeZone("Etc/UTC")
    return "${calendar.get(Calendar.MONTH) + 1}.${calendar.get(Calendar.DATE)}"
}

fun setTodayDate() : String {
    val simpleDateFormat = SimpleDateFormat("MM.dd", Locale.KOREA)
    return simpleDateFormat.format(Calendar.getInstance().time)
}