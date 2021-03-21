package com.example.acrv.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.acrv.repository.UserRepository
import com.example.acrv.roomData.UserDatabase
import com.example.acrv.roomModel.UserCityWeather
import kotlinx.coroutines.launch

class UserCityWeatherViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<UserCityWeather>>
    private val repository: UserRepository
    lateinit var searchedUserCity: LiveData<List<UserCityWeather>>

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData.asLiveData()
    }


    fun addUserCityWeather(userCityWeather: UserCityWeather){
        viewModelScope.launch {
            repository.addUserCityWeather(userCityWeather)
        }
    }


    fun searchDatabase(searchedQuery: String): LiveData<List<UserCityWeather>>{
        return repository.searchDatabase(searchedQuery).asLiveData()
    }

    fun deleteUserCityWeather(userCityWeather: UserCityWeather) {
        viewModelScope.launch {
            repository.deleteUserCityWeather(userCityWeather)
        }
    }

    fun updateUserCityWeather(Rain: String,  Weather: String, Max_Temp: Double, Min_Temp: Double, Temp: Double, Humidity: Int, Wind: Double,inputCityName: String) {
        viewModelScope.launch {
            repository.updateUserCityWeather(Rain,Weather,Max_Temp,Min_Temp, Temp, Humidity, Wind, inputCityName)
        }
    }
}