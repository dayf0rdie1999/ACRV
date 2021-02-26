package com.example.acrv.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acrv.modelpackage.citiesmodel.CitiesWeather
import com.example.acrv.modelpackage.model.CoordWeather
import com.example.acrv.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel(){

    val myResponse: MutableLiveData<Response<CoordWeather>> = MutableLiveData()
    val myCitiesWeather: MutableLiveData<Response<CitiesWeather>> = MutableLiveData()

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

}