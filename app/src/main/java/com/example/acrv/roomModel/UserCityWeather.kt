package com.example.acrv.roomModel

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "user_cities_weather")
@Parcelize
data class UserCityWeather (
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val cityName: String
):Parcelable