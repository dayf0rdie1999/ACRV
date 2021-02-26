package com.example.acrv.roomModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_cities_weather")
data class UserCityWeather (
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val cityName: String
        )