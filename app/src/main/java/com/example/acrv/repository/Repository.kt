package com.example.acrv.repository

import com.example.acrv.api.RetrofitInstance
import com.example.acrv.modelpackage.citiesmodel.CitiesWeather
import com.example.acrv.modelpackage.model.CoordWeather
import retrofit2.Response

class Repository {

    suspend fun getWeather(): Response<CoordWeather>{
        return RetrofitInstance.api.getWeather()
    }

    suspend fun getCitiesWeather(): Response<CitiesWeather> {
        return RetrofitInstance.api.getCitiesWeather()
    }

}