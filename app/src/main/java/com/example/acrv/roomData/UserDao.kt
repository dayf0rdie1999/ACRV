package com.example.acrv.roomData

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.acrv.roomModel.UserCityWeather

// This is interface which is a protocol which can initialize the equation without the bodies, but
// it communicate outside of the application such as SQL and Json
@Dao
interface UserDao {

    @Query("SELECT * FROM user_cities_weather")
    fun readAllData(): LiveData<List<UserCityWeather>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addUserCityWeather(userCityWeather: UserCityWeather)

    @Delete
    suspend fun deleteUserCityWeather(userCityWeather: UserCityWeather)

}