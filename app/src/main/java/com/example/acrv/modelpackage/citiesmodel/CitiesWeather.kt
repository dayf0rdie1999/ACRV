package com.example.acrv.modelpackage.citiesmodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize



data class CitiesWeather (
    val calctime: Double,
    val cnt: Int,
    val cod: Int,
    val list: List<CityWeather>
)