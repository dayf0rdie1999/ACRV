package com.example.acrv.fragments

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.acrv.R
import com.example.acrv.fragments.citiesweatherforecast.CitiesWeatherAdapter
import com.example.acrv.repository.Repository
import com.example.acrv.viewmodel.MainViewModel
import com.example.acrv.viewmodelfactory.MainViewModelFactory

class weatherForeCastFragment : Fragment() {

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
                response.body()?.list!!.forEach {
                    it ->
                    //  Putting them in recylcerview

                    // Putting them in a dialog
                    d("Name","${it.name}")
                    d("Dash","------------------------------")
                    }


            } else {
                d("Error","${response.code()}")
            }
        })

        return view
    }

}