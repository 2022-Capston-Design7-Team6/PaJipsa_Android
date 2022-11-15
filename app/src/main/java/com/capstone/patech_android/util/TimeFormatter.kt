package com.capstone.patech_android.util

import java.text.SimpleDateFormat
import java.util.*

fun timeFormatToCalender(inputDate: String): Calendar? {
    val format = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'"
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.time = sdf.parse(inputDate) ?: return null
    calendar.timeZone = TimeZone.getTimeZone("Etc/UTC")
    return calendar
}
