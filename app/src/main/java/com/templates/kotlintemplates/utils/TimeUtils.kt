package com.dsdmsa.weather.utils

import android.text.format.DateFormat
import java.util.*


var calendar: Calendar? = null

fun Long.getTime(format: String): String {
    calendar = Calendar.getInstance()
    if (calendar != null) {
        calendar?.timeInMillis = this
        val time: String = DateFormat.format(format, calendar).toString()
        return time
    }
    return ""
}
