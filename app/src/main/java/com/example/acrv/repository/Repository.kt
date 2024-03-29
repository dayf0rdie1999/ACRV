package com.example.acrv.repository

import androidx.lifecycle.MutableLiveData
import com.example.acrv.api.RetrofitInstance
import com.example.acrv.modelpackage.citiesmodel.CitiesWeather
import com.example.acrv.modelpackage.model.CoordWeather
import com.example.acrv.util.Constants
import retrofit2.Response

class Repository {

    suspend fun getWeather(): Response<CoordWeather>{
        return RetrofitInstance.api.getWeather()
    }

    // Creating a function to read get all the data
    suspend fun getCitiesWeather(): Response<CitiesWeather> {
        return RetrofitInstance.api.getCitiesWeather()
    }

    // Creating a function to implement the Interface from simpleApi
    suspend fun getCityWeather(name: String): Response<CoordWeather> {
        return RetrofitInstance.api.getCityWeather(name, Constants.KEY)
    }

    suspend fun newGetCitiesWeather(): Response<CitiesWeather> {
        return RetrofitInstance.api.newGetCitiesWeather()
    }

}