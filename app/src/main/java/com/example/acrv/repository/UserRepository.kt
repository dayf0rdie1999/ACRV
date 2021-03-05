package com.example.acrv.repository

import androidx.lifecycle.LiveData
import com.example.acrv.roomData.UserDao
import com.example.acrv.roomModel.CitiesModel
import com.example.acrv.roomModel.UserCityWeather
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    /**
     * Since the function doesn't require any input, it can be declared here and pass to view model
     *
     * **/
    val readAllData: Flow<List<UserCityWeather>> = userDao.readAllData()
    val readAllCityData: LiveData<List<CitiesModel>> = userDao.readAllCityData()

    /**
     * These are function to apply logic to abstract function from UserDao interface
     * **/

    suspend fun addUserCityWeather(userCityWeather: UserCityWeather){
        userDao.addUserCityWeather(userCityWeather)
    }

    suspend fun deleteUserCityWeather(userCityWeather: UserCityWeather){
        userDao.deleteUserCityWeather(userCityWeather)
    }

    fun searchDatabase(searchQuery: String): Flow<List<UserCityWeather>> {
        return userDao.searchDatabase(searchQuery)
    }

    /**
     * These are function to apply logic to abstract function from CityDao interface
     * **/

    suspend fun addCityModel(cityModel: CitiesModel){
        userDao.addCityModel(cityModel)
    }

    fun citiesSearchDatabase(searchQuery: String):Flow<List<CitiesModel>> {
        return userDao.citiesSearchDatabase(searchQuery)
    }

}