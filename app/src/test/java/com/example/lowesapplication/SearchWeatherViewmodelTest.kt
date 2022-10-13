package com.example.lowesapplication

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.example.lowesapplication.model.City
import com.example.lowesapplication.model.Coord
import com.example.lowesapplication.model.List
import com.example.lowesapplication.model.WeatherData
import com.example.lowesapplication.retrofit.WeatherRepository
import com.example.lowesapplication.viewmodel.SearchWeatherViewModel
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SearchWeatherViewmodelTest {
    private val weatherRepository: WeatherRepository = mockk()
    private val response: Response<WeatherData> = mockk(relaxed = true)
    val viewModel = SearchWeatherViewModel(weatherRepository)
    val lifeCycleScope : LifecycleOwner = mockk()

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun getWeatherTest() {
        runBlocking {
            launch(Dispatchers.Main) {
                coEvery {
                    weatherRepository.getWeatherData("Denver")
                } returns Response.success(getMockWeatherData().value)
                viewModel.getWeatherData("Denver")
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    private fun getMockWeatherData(): MutableLiveData<WeatherData> {
        val list:ArrayList<List> = mockk(relaxed = true)
        return MutableLiveData(
            WeatherData(
            city = City(
                id = 123,
                name = "Denver",
                coord = Coord(lat = 12.12, lon = 45.45),
                country = "USA",
                population = 123456,
                timezone = 78945,
                sunrise = 1600,
                sunset = 4587
            ),
            cnt = 111,
            cod = "cod",
            list = list,
            message = 222
        )
        )
    }
}