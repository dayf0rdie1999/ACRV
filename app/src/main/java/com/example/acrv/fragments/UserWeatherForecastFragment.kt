package com.example.acrv.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.acrv.R
import com.example.acrv.repository.Repository
import com.example.acrv.viewmodel.MainViewModel
import com.example.acrv.viewmodelfactory.MainViewModelFactory

class userWeatherForecastFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_user_weather_forecast, container, false)

        return view
    }

}