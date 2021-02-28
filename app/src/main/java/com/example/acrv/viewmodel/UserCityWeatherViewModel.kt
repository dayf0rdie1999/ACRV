package com.example.acrv.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.acrv.repository.UserRepository
import com.example.acrv.roomData.UserDatabase
import com.example.acrv.roomModel.UserCityWeather
import kotlinx.coroutines.launch

class UserCityWeatherViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<UserCityWeather>>
    private val repository: UserRepository


    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }


    fun addUserCityWeather(userCityWeather: UserCityWeather){
        viewModelScope.launch {
            repository.addUserCityWeather(userCityWeather)
        }
    }

    fun deleteUserCityWeather(userCityWeather: UserCityWeather){
        viewModelScope.launch{
            repository.deleteUserCityWeather(userCityWeather)
        }
    }
}