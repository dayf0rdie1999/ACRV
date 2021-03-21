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


    fun searchDatabase(searchQuery: String): Flow<List<UserCityWeather>> {
        return userDao.searchDatabase(searchQuery)
    }

    suspend fun deleteUserCityWeather(userCityWeather: UserCityWeather) {
        return userDao.deleteUserCityWeather(userCityWeather)
    }

    suspend fun updateUserCityWeather(Rain: String,  Weather: String, Max_Temp: Double, Min_Temp: Double, Temp: Double, Humidity: Int, Wind: Double,inputCityName: String) {
        return userDao.updateUserCityWeather(Rain,Weather,Max_Temp,Min_Temp,Temp,Humidity,Wind,inputCityName)
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

    suspend fun updateDatabase(cityModel: CitiesModel) {
        return userDao.updateCitiesModel(cityModel)
    }

    fun getDataSize(): Int{
        return userDao.getDataSize()
    }

}