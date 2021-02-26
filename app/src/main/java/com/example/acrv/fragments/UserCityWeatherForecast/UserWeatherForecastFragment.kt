package com.example.acrv.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.acrv.R
import com.example.acrv.fragments.UserCityWeatherForecast.UserCityWeatherAdapter
import com.example.acrv.repository.Repository
import com.example.acrv.viewmodel.MainViewModel
import com.example.acrv.viewmodel.UserCityWeatherViewModel
import com.example.acrv.viewmodelfactory.MainViewModelFactory

class userWeatherForecastFragment : Fragment() {

    private lateinit var mUserCityWeatherViewModel: UserCityWeatherViewModel
    val userCityWeatherAdapter by lazy {
        UserCityWeatherAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_user_weather_forecast, container, false)

        // Adding the RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.userCityWeather_rv)
        recyclerView.adapter = userCityWeatherAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity().applicationContext)

        mUserCityWeatherViewModel = ViewModelProvider(this).get(UserCityWeatherViewModel::class.java)

        mUserCityWeatherViewModel.readAllData.observe(viewLifecycleOwner, {
            userCityWeatherAdapter.setData(it)
        })


        return view
    }

}