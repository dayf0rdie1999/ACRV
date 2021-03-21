package com.example.acrv.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.acrv.modelpackage.citiesmodel.CitiesWeather
import com.example.acrv.modelpackage.citiesmodel.CityWeather
import com.example.acrv.modelpackage.model.CoordWeather
import com.example.acrv.repository.Repository
import com.example.acrv.repository.UserRepository
import com.example.acrv.util.Constants
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel(){

    val myResponse: MutableLiveData<Response<CoordWeather>> = MutableLiveData()
    val myCitiesWeather: MutableLiveData<Response<CitiesWeather>> = MutableLiveData()
    val myCityWeather: MutableLiveData<Response<CoordWeather>> = MutableLiveData()

    fun getWeather() {
        viewModelScope.launch {
            val weatherResponse = repository.getWeather()
            myResponse.value = weatherResponse
        }
    }

    fun getCitiesWeather() {
        viewModelScope.launch {
            val citiesWeatherResponse = repository.getCitiesWeather()
            myCitiesWeather.value = citiesWeatherResponse
        }

    }

    // Creating a function to run the function in the background
    fun getCityWeather(name: String) {
        viewModelScope.launch {
            val cityWeatherResponse = repository.getCityWeather(name)
            myCityWeather.value = cityWeatherResponse
        }
    }

}