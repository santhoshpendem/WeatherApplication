package com.example.lowesapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.lowesapplication.model.List
import com.example.lowesapplication.util.kelvinToFahrenheit
import com.example.lowesapplication.viewmodel.SearchWeatherViewModel

class WeatherAdapter(
    val weatherList:ArrayList<List>?,
    viewModel: SearchWeatherViewModel
): RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
     var viewModel: SearchWeatherViewModel = viewModel

    inner class WeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tempText: TextView = itemView.findViewById(R.id.compact_temp)
        val dateText: TextView = itemView.findViewById(R.id.compact_date)
        val feelsLikeText: TextView = itemView.findViewById(R.id.compact_feels_like)
        val visibilityText: TextView = itemView.findViewById(R.id.compact_visibility)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.compact_weather_view,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather = weatherList?.get(position)

        holder.apply {
            tempText.text =  this.itemView.resources.getString(
                R.string.temperature,
                kelvinToFahrenheit(weather?.main?.temp)
            )
            dateText.text = this.itemView.resources.getString(
                R.string.date,
                weather?.dt_txt?.substring(0,10)
            )
            feelsLikeText.text = this.itemView.resources.getString(
                R.string.feels_like,
                kelvinToFahrenheit(weather?.main?.feelsLike)
            )
            visibilityText.text = this.itemView.resources.getString(
                R.string.visibility,
                weather?.visibility.toString()
            )
            this.itemView.setOnClickListener {
                viewModel.selectedWeatherItem.value = weather
                it.findNavController().navigate(R.id.action_weatherListFragment_to_fullWeatherViewFragment)
            }
        }
    }

    override fun getItemCount(): Int {
        return weatherList?.size ?: 0
    }
}