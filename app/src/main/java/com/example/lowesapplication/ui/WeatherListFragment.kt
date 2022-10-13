package com.example.lowesapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lowesapplication.*
import com.example.lowesapplication.util.Resource
import com.example.lowesapplication.viewmodel.SearchWeatherViewModel
import com.google.android.material.appbar.MaterialToolbar


/**
 * A simple [Fragment] subclass.
 * Use the [WeatherListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WeatherListFragment : Fragment() {

    lateinit var viewModel: SearchWeatherViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var views: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        views =  inflater.inflate(R.layout.weather_list_fragment, container, false)
        viewModel = (activity as MainActivity).viewModel

        return views
    }

    override fun onStart() {
        super.onStart()
        val toolbar = (activity as MainActivity).toolbar

        setUpToolbar(toolbar)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rv_weather)
        progressBar = view.findViewById(R.id.progressBar)

        viewModel.weatherData.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { finalResponse ->
                        setUpRecyclerView(WeatherAdapter(finalResponse.list, viewModel))
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    viewModel.enteredCity.value = ""
                    (activity as MainActivity).navigateUp()
                    Toast.makeText(
                        view.context,
                        "Please enter a valid city name",
                        Toast.LENGTH_LONG
                    ).show()
                    response.message?.let { message ->
                        Log.e("WeatherListFragment", "An Error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }


    private fun setUpToolbar(toolbar: MaterialToolbar) {
        toolbar.apply {
            title = viewModel.enteredCity.value
            setTitleTextColor(resources.getColor(R.color.black))
            visibility = View.VISIBLE
            setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
            setBackgroundColor(resources.getColor(R.color.purple_200))
            setNavigationOnClickListener {
                (activity as MainActivity).navigateUp()
               // views.findNavController().popBackStack()
            }
        }
    }

    private fun setUpRecyclerView(weatherAdapter: WeatherAdapter) {
        recyclerView.apply {
            adapter = weatherAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    /**
     * This function is used set the visibility of the progress bar to GONE
     */
    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    /**
     * This function is used set the visibility of the progress bar to VISIBLE
     */
    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

}