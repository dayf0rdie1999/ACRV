package com.example.acrv.fragments

import android.os.Bundle
import android.util.Log.d
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.acrv.R
import com.example.acrv.fragments.citiesweatherforecast.CitiesWeatherAdapter
import com.example.acrv.modelpackage.citiesmodel.CityWeather
import com.example.acrv.repository.Repository
import com.example.acrv.viewmodel.MainViewModel
import com.example.acrv.viewmodelfactory.MainViewModelFactory
import java.util.Collections.list

class weatherForeCastFragment : Fragment(),SearchView.OnQueryTextListener {

    private lateinit var mainViewModel: MainViewModel
    private val myCitiesWeatherAdapter by lazy {
        CitiesWeatherAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_weather_fore_cast, container, false)

        // Creating a recylcerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.citiesWeather_RV)
        recyclerView.adapter = myCitiesWeatherAdapter;
        recyclerView.layoutManager = LinearLayoutManager(requireActivity().applicationContext)

        val repository =  Repository()

        val viewModelFactory = MainViewModelFactory(repository)

        mainViewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

        mainViewModel.getCitiesWeather()

        mainViewModel.myCitiesWeather.observe(viewLifecycleOwner, Observer{
                response ->
            if (response.isSuccessful) {
                response.body()?.list!!.let {
                    myCitiesWeatherAdapter.setData(it)
                }
            } else {
                Toast.makeText(requireActivity().applicationContext,"${response.code()} Can't find the city", Toast.LENGTH_LONG)
            }
        })

        // Notify the mainActivity that we have optionsMenu
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_cities_weather_forecast,menu)

        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            getCityWeather(query)
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        return true
    }

    private fun getCityWeather(query: String?) {
        // observing the object while it is running and store the data in the background
        mainViewModel.myCitiesWeather.observe(viewLifecycleOwner,{
            response ->
            if (response.isSuccessful && response.body()?.list!!
                    .filter { it.name == query } != null) {
                response.body()?.list!!
                    .filter { it.name == query }
                    .let {
                        val action = weatherForeCastFragmentDirections.actionWeatherForeCastFragmentToCityWeatherFragment(it[0])
                        findNavController().navigate(action)
                    }
                }else {
                Toast.makeText(requireActivity().applicationContext,"Can't find the city", Toast.LENGTH_LONG)
            }
        })
    }


}