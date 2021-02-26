package com.example.acrv.modelpackage.model

data class CoordWeather(
        val base: String,
        val clouds: Clouds,
        val cod: Int,
        val coord: Coord,
        val dt: Int,
        val id: Int,
        val main: Main,
        val name: String,
        val sys: Sys,
        val timezone: Int,
        val visibility: Int,
        val weather: List<Weather>,
        val wind: Wind
)