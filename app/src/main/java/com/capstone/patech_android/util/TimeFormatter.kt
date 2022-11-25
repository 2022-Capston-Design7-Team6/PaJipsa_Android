package com.capstone.patech_android.util

import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

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

class GraphAxisValueFormat : IndexAxisValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        val valueToDays = TimeUnit.DAYS.toMillis(value.toLong())
        val timeDate = Date(valueToDays)
        val formatDate = SimpleDateFormat("MM.dd", Locale.getDefault())
        return formatDate.format(timeDate)
    }
}