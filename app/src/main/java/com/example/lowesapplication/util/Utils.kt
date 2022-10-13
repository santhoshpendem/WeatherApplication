package com.example.lowesapplication.util

import android.annotation.SuppressLint
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Calendar

fun kelvinToFahrenheit(tempInKelvin: Double?): String {
    if(tempInKelvin != 0.0) {
        val df = DecimalFormat("0.00")
        val fahrenheit = ((tempInKelvin?.minus(273.15))?.times(1.8))?.plus(32)
        return df.format(fahrenheit).toString()
    } else {
        return "0.00"
    }
}