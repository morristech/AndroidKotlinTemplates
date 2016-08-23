package com.dsdmsa.weather.utils

fun Float.toCelsius(): String {
    return (this - 273.15).toInt().toString()
}

