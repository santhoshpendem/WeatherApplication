package com.example.lowesapplication.retrofit

import com.example.lowesapplication.model.WeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/forecast")
    suspend fun getWeather(
        @Query("q")
        city: String = "sterling",
        @Query("appid")
        appId: String = "65d00499677e59496ca2f318eb68c049"
    ): Response<WeatherData>
}