package com.example.acrv.repository

import com.example.acrv.api.RetrofitInstance
import com.example.acrv.modelpackage.citiesmodel.CitiesWeather
import com.example.acrv.modelpackage.model.CoordWeather
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
        return RetrofitInstance.api.getCityWeather(name)
    }

}