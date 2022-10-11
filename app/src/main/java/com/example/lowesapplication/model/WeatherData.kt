package com.example.lowesapplication.model

data class WeatherData(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: ArrayList<List>,
    val message: Int
)

data class City(
    val id: Int,
    val name: String,
    val coord: Coord,
    val country: String,
    val population: Int,
    val timezone: Int,
    val sunrise: Int,
    val sunset: Int
)

data class Coord(
    val lat: Double,
    val lon: Double
)

data class List(
    val dt: Int,
    val main: Main,
    val weather: ArrayList<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Int,
    val pop: Double,
    val sys: Sys,
    val dt_txt: String
)

data class Main(
    val temp: Double,
    val feelsLike: Double,
    val tempMin: Double,
    val tempMax: Double,
    val pressure: Int,
    val seaLevel: Int,
    val grndLevel: Int,
    val humidity: Int,
    val tempKf: Double
)

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)

data class Clouds(
    val all: Int
)

data class Wind(
    val deg: Int,
    val gust: Double,
    val speed: Double
)

data class Rain(
    val `3h`: Double
)

data class Sys(
    val pod: String
)



