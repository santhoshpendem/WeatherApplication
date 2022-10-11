package com.example.lowesapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lowesapplication.model.List
import com.example.lowesapplication.util.Resource
import com.example.lowesapplication.model.WeatherData
import com.example.lowesapplication.retrofit.WeatherRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchWeatherViewModel(
    val weatherRepository: WeatherRepository
): ViewModel() {

    val weatherData: MutableLiveData<Resource<WeatherData>> = MutableLiveData()
    val selectedWeatherItem: MutableLiveData<List> = MutableLiveData()
    val enteredCity: MutableLiveData<String> = MutableLiveData()

    fun getWeatherData(city: String) {
        viewModelScope.launch {
            weatherData.postValue(Resource.Loading())
            val response = weatherRepository.getWeatherData(city)
            weatherData.postValue(handleWeatherData(response))
        }
    }

    private fun handleWeatherData(response: Response<WeatherData>) : Resource<WeatherData> {
        if(response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}