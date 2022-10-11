package com.example.lowesapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.lowesapplication.MainActivity
import com.example.lowesapplication.R
import com.example.lowesapplication.viewmodel.SearchWeatherViewModel
import com.example.lowesapplication.util.kelvinToFahrenheit

class FullWeatherViewFragment : Fragment() {

    lateinit var temperature : TextView
    lateinit var humidity : TextView
    lateinit var feelsLike : TextView
    lateinit var clouds : TextView
    lateinit var cloudsDesc : TextView
    lateinit var maxTemp : TextView
    lateinit var minTemp : TextView
    lateinit var windSpeed : TextView
    lateinit var viewModel: SearchWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.full_weather_view_fragment, container, false)
        temperature = view.findViewById(R.id.temperature)
        humidity = view.findViewById(R.id.humidity)
        feelsLike = view.findViewById(R.id.feels_like)
        cloudsDesc = view.findViewById(R.id.clouds_desc)
        maxTemp = view.findViewById(R.id.max_temp)
        minTemp = view.findViewById(R.id.min_temp)
        windSpeed = view.findViewById(R.id.wind_speed)
        viewModel = (activity as MainActivity).viewModel

        val toolbar = (activity as MainActivity).toolbar
        toolbar.apply {
            visibility = View.VISIBLE
            setTitleTextColor(resources.getColor(R.color.black))
            title = viewModel.enteredCity.value
            setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
            setBackgroundColor(resources.getColor(R.color.purple_700))
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }

        viewModel.selectedWeatherItem.value.apply {
            temperature.text = kelvinToFahrenheit(this?.main?.temp)
            humidity.text = view.resources.getString(R.string.humidity,this?.main?.humidity.toString())
            feelsLike.text = view.resources.getString(R.string.feels_like, kelvinToFahrenheit(this?.main?.feelsLike))
            cloudsDesc.text = this?.weather?.get(0)?.description.toString()
            maxTemp.text = view.resources.getString(R.string.max_temp, kelvinToFahrenheit(this?.main?.tempMax))
            minTemp.text = view.resources.getString(
                R.string.min_temp,
                kelvinToFahrenheit(this?.main?.tempMin)
            )
            windSpeed.text = view.resources.getString(R.string.wind_speed,this?.wind?.speed.toString())
        }
        return view
    }

}