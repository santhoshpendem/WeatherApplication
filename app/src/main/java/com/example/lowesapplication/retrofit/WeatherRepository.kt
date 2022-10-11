package com.example.lowesapplication.retrofit

import com.example.lowesapplication.retrofit.RetrofitInstance

class WeatherRepository {

    /**
     * Suspend function to make the weather api call with city as query string.
     */
    suspend fun getWeatherData(cityName: String) =
        RetrofitInstance.api.getWeather(cityName)
}