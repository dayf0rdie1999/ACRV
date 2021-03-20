package com.example.acrv.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.acrv.R
import com.example.acrv.databinding.FragmentWeatherForeCastBinding
import com.example.acrv.fragments.citiesweatherforecast.CitiesWeatherAdapter
import com.example.acrv.modelpackage.citiesmodel.CityWeather
import com.example.acrv.repository.Repository
import com.example.acrv.roomModel.CitiesModel
import com.example.acrv.viewmodel.CityModelViewModel
import com.example.acrv.viewmodel.MainViewModel
import com.example.acrv.viewmodelfactory.MainViewModelFactory
import java.time.LocalTime
import java.util.*

class weatherForeCastFragment : Fragment(),SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentWeatherForeCastBinding
    // declare the variable for everywhere in the class to access
    private lateinit var mainViewModel: MainViewModel
    private lateinit var mCitiesModelViewModel: CityModelViewModel

    // Initializing the object using lazy
    private val myCitiesWeatherAdapter by lazy {
        CitiesWeatherAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWeatherForeCastBinding.inflate(layoutInflater)

        // Creating a recylcerView
        binding.citiesWeatherRV.adapter = myCitiesWeatherAdapter;
        binding.citiesWeatherRV.layoutManager = LinearLayoutManager(requireActivity().applicationContext)

        // Initializing CitiesModelViewModel
        mCitiesModelViewModel = ViewModelProvider(this).get(CityModelViewModel::class.java)

        // Initializing repository class where all the function is created
        val repository =  Repository()

        // Initializing the factory and viewmodel to implement the calling equations as background
        val viewModelFactory = MainViewModelFactory(repository)
        mainViewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

        // Setting the timer to run every 60 mintues
        mainViewModel.getCitiesWeather()

        // Observing the data when the object at background
        mainViewModel.myCitiesWeather.observe(viewLifecycleOwner, Observer{
                response ->
            if (response.isSuccessful) {
                response.body()?.list!!.let {
                    insertCityNameToDatabase(it)
                }
            } else {
                Toast.makeText(requireActivity().applicationContext,"${response.code()} Can't find the city", Toast.LENGTH_LONG)
            }
        })

        mCitiesModelViewModel.readAllCityNameData.observe(viewLifecycleOwner,{ CitiesModelList ->
            myCitiesWeatherAdapter.setData(CitiesModelList)
        })

        // Notify the mainActivity that we have optionsMenu
        setHasOptionsMenu(true)

        // Set on click listener of the floating button to navigate to different view
        binding.personalFragmentFbt.setOnClickListener{
            binding.root.findNavController().navigate(R.id.userWeatherForecastFragment)
        }

        return binding.root

    }

    /** Inserting Data into CitiesModel Once in application life cycle **/
    private fun insertCityNameToDatabase(Cities: List<CityWeather>) {
        // Observe the data
        mCitiesModelViewModel.readAllCityNameData.observe(viewLifecycleOwner,{
            when {
                it.isEmpty() -> {
                    addCitiesToDatabase(Cities)
                }
            }
        })
    }

    private fun addCitiesToDatabase(Cities: List<CityWeather>) {
        var currentTime = "${LocalTime.now().hour}:${LocalTime.now().minute}"
        Cities.forEach { city ->
            if (city.rain == null) {
                var city = CitiesModel(0, city.name, "No Rain",currentTime)
                mCitiesModelViewModel.addCityModel(city)
            } else {
                var city = CitiesModel(0, city.name, "Rain",currentTime)
                mCitiesModelViewModel.addCityModel(city)
            }
        }
    }


    // Override function to add the menu item onto the supportActionBar (toolbar)
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Pre-create the menu item on the menu
        inflater.inflate(R.menu.search_cities_weather_forecast,menu)

        // binding the varible to the view
        val search = menu?.findItem(R.id.menu_search)

        // binding the view with its action and treated as an object
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)

        // Assigning the pre-created menu item into the supportActionBar
        super.onCreateOptionsMenu(menu, inflater)
    }

    /**
     * onQueryTextSubmit() is a function that triggering certain action when the text being submitted
     * onQueryTetChange() is a function that triggering actions when the text is changing
     * getCityWeather() is a function that
     * **/

    // The function is called when the search button is clicked
    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            getCityWeather(query)
        }
        return true
    }

    // The function is called when the text is changing in the search edit text
    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            getCityWeather(query)
        }
        return true
    }

    // Function to check whether the name of the city exists and then navigate to the detail information of that city
    private fun getCityWeather(query: String?) {
        // observing the object while it is running and store the data in the background
        var searchQuery = "%$query%"

        mCitiesModelViewModel.citySearchModel(searchQuery).observe(viewLifecycleOwner,{
            myCitiesWeatherAdapter.setData(it)
        })

    }
}