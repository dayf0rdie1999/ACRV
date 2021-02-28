package com.example.acrv.repository

import androidx.lifecycle.LiveData
import com.example.acrv.roomData.UserDao
import com.example.acrv.roomModel.UserCityWeather

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<UserCityWeather>> = userDao.readAllData()


    suspend fun addUserCityWeather(userCityWeather: UserCityWeather){
        userDao.addUserCityWeather(userCityWeather)
    }
    // Hello World
    suspend fun deleteUserCityWeather(userCityWeather: UserCityWeather){
        userDao.deleteUserCityWeather(userCityWeather)
    }

}