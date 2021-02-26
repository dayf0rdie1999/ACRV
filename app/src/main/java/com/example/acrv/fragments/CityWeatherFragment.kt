package com.example.acrv.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.acrv.R

class cityWeatherFragment : Fragment() {

    private val args by navArgs<cityWeatherFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_city_weather, container, false)

        val cityName = view.findViewById<TextView>(R.id.cityNameCityFragment_tv)
        val rain = view.findViewById<TextView>(R.id.rainCityFragment_tv)


        cityName.text = args.cityWeatherInformation.name

        if (args.cityWeatherInformation.rain == null) {
            rain.text = "No Rain"
        }else {
            rain.text = "Rain"
        }



        return view
    }

}