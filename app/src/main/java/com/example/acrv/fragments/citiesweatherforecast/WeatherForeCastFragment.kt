package com.example.acrv.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.acrv.R
import com.example.acrv.databinding.FragmentWeatherForeCastBinding
import com.example.acrv.fragments.citiesweatherforecast.CitiesWeatherAdapter
import com.example.acrv.repository.Repository
import com.example.acrv.viewmodel.CityModelViewModel
import com.example.acrv.viewmodel.MainViewModel
import com.example.acrv.Worker.UpdateWorker
import com.example.acrv.Worker.UploadWorker
import com.example.acrv.viewmodelfactory.MainViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class weatherForeCastFragment : Fragment(),SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentWeatherForeCastBinding
    // declare the variable for everywhere in the class to access
    private lateinit var mainViewModel: MainViewModel
    private lateinit var mCitiesModelViewModel: CityModelViewModel


    private val repository by lazy {
        Repository()
    }

    private val mainViewModelFactory by lazy {
        MainViewModelFactory(repository)
    }

    // Initializing the object using lazy
    private val myCitiesWeatherAdapter by lazy {
        CitiesWeatherAdapter()
    }


    // Declare the WorkManager
    val workManager: WorkManager by lazy {
        WorkManager.getInstance(requireContext().applicationContext)
    }

    val updateWorker = PeriodicWorkRequestBuilder<UpdateWorker>(15, TimeUnit.MINUTES)
        .build()

    val uploadWorker = OneTimeWorkRequestBuilder<UploadWorker>()
        .build()

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


        /**
         * Uploading the data to the database if the object hasn't been created
         * Setting WorkManager to update the data every 15 Minutes
         */

        GlobalScope.launch {
            if (mCitiesModelViewModel.getDataSize() == 0) {
                workManager.enqueue(uploadWorker)
            }else {
                workManager.enqueue(updateWorker)
            }
        }

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
