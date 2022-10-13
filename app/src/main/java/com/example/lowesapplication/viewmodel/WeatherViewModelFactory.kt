package com.example.lowesapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lowesapplication.retrofit.WeatherRepository

class WeatherViewModelFactory(
    private val weatherRepository: WeatherRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchWeatherViewModel(weatherRepository) as T
    }
}