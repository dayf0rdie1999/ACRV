package com.example.acrv.modelpackage.citiesmodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class CityWeather(
        val clouds: @RawValue Clouds,
        val coord: @RawValue Coord,
        val dt: Int,
        val id: Int,
        val main: @RawValue Main,
        val name: String,
        val rain: @RawValue Any,
        val snow: @RawValue Any,
        val visibility: Int,
        val weather: @RawValue List<Weather>,
        val wind: @RawValue Wind
):Parcelable