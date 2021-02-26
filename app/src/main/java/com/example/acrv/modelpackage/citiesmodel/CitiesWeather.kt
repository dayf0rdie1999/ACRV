package com.example.acrv.modelpackage.citiesmodel


data class CitiesWeather (
    val calctime: Double,
    val cnt: Int,
    val cod: Int,
    val list: List<CityWeather>
)