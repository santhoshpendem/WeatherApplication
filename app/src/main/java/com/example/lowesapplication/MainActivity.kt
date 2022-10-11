package com.example.lowesapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.lowesapplication.retrofit.WeatherRepository
import com.example.lowesapplication.viewmodel.SearchWeatherViewModel
import com.example.lowesapplication.viewmodel.WeatherViewModelFactory
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    lateinit var viewModel: SearchWeatherViewModel
    lateinit var toolbar: MaterialToolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        val repository = WeatherRepository()
        val viewModelProviderFactory = WeatherViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(SearchWeatherViewModel::class.java)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.weatherNavHostFragment) as NavHostFragment

        navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.weather_nav_graph)
        navController.graph = navGraph
        toolbar.setupWithNavController(navController, AppBarConfiguration(navGraph))
    }
}
