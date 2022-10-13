package com.example.lowesapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.lowesapplication.retrofit.WeatherRepository
import com.example.lowesapplication.viewmodel.SearchWeatherViewModel
import com.example.lowesapplication.viewmodel.WeatherViewModelFactory
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity(), NavigationUi {

    lateinit var viewModel: SearchWeatherViewModel
    lateinit var toolbar: MaterialToolbar
    private val navController by lazy {
        (supportFragmentManager
            .findFragmentById(R.id.weatherNavHostFragment) as NavHostFragment).navController
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        val repository = WeatherRepository()
        val viewModelProviderFactory = WeatherViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(SearchWeatherViewModel::class.java)

        val navGraph = navController.navInflater.inflate(R.navigation.weather_nav_graph)
        toolbar.setupWithNavController(navController, AppBarConfiguration(navGraph))
    }

    override fun finishActivity() {
        this.finish()
    }

    override fun navigateUp() {
        navController.popBackStack()
    }

    override fun navigateTo(actionId : Int) {
        navController.navigate(actionId)
    }
}

internal interface NavigationUi {
    /**
     * Method to be overridden to close the activity
     */
    fun finishActivity()

    /**
     * Method to be overridden to navigate up
     */
    fun navigateUp()

    /**
     * Method to be overridden to to navigate to particular screen
     */
    fun navigateTo(actionId: Int)
}
