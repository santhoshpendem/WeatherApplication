package com.example.lowesapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lowesapplication.MainActivity
import com.example.lowesapplication.R
import com.example.lowesapplication.viewmodel.SearchWeatherViewModel

class SearchCityWeatherFragment: Fragment() {

    lateinit var viewModel: SearchWeatherViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.weather_lookup_fragment, container, false)
        val button = view.findViewById<Button>(R.id.look_up_city_weather)
        val cityName = view.findViewById<EditText>(R.id.enter_city_name)

        val toolbar = (activity as MainActivity).toolbar
        toolbar.visibility = View.GONE

        viewModel = (activity as MainActivity).viewModel
        button.setOnClickListener {
            viewModel.enteredCity.value = cityName.text.toString()
            viewModel.getWeatherData(cityName.text.toString())
            findNavController().navigate(R.id.action_SearchCityWeatherFragment_to_weatherListFragment)
        }
        return view
    }
}