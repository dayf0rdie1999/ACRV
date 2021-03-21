package com.example.acrv.api

import com.example.acrv.modelpackage.citiesmodel.CitiesWeather
import com.example.acrv.modelpackage.model.CoordWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SimpleApi {

    @GET("data/2.5/weather?lat=29&lon=-81&appid=0b636a698686b7998b1705da6012620c")
    suspend fun getWeather(): Response<CoordWeather>

    @GET("data/2.5/box/city?bbox=-83,25,-79,30,10&appid=0b636a698686b7998b1705da6012620c")
    suspend fun getCitiesWeather(): Response<CitiesWeather>

    @GET("data/2.5/weather")
    suspend fun getCityWeather(
        @Query("q") name: String,
        @Query("appid") key: String
    ):Response<CoordWeather>


    @GET("data/2.5/box/city?bbox=-83,25,-79,30,10&appid=0b636a698686b7998b1705da6012620c")
    suspend fun newGetCitiesWeather(): Response<CitiesWeather>

}