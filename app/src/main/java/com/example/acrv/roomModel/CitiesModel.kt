package com.example.acrv.roomModel

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "cities_Name_table")
@Parcelize
data class CitiesModel(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val cityId: Int,
        val cityName: String,
        val rain: String,
        val weather: String,
        val max_temp: Double,
        val min_temp: Double,
        val temp: Double,
        val humidity: Int,
        val wind: Double
):Parcelable