package com.example.acrv.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.acrv.repository.UserRepository
import com.example.acrv.roomData.UserDatabase
import com.example.acrv.roomModel.CitiesModel
import kotlinx.coroutines.launch

class CityModelViewModel(application: Application): AndroidViewModel(application){

    val readAllCityNameData: LiveData<List<CitiesModel>>
    private val citiesRepository: UserRepository


    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        citiesRepository = UserRepository(userDao)
        readAllCityNameData = citiesRepository.readAllCityData
    }

    fun addCityModel(cityModel: CitiesModel) {
        viewModelScope.launch {
            citiesRepository.addCityModel(cityModel)
        }
    }

    fun citySearchModel(searchQuery: String): LiveData<List<CitiesModel>>{
        return citiesRepository.citiesSearchDatabase(searchQuery).asLiveData()
    }

    fun updateDatabase(cityModel: CitiesModel) {
        viewModelScope.launch {
            citiesRepository.updateDatabase(cityModel)
        }
    }

    fun getDataSize(): Int {
        return citiesRepository.getDataSize()
    }
}