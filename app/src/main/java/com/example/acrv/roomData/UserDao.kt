package com.example.acrv.roomData

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.acrv.roomModel.CitiesModel
import com.example.acrv.roomModel.UserCityWeather
import kotlinx.coroutines.flow.Flow

// This is interface which is a protocol which can initialize the equation without the bodies, but
// it communicate outside of the application such as SQL and Json
@Dao
interface UserDao {

    @Query("SELECT * FROM user_cities_weather")
    fun readAllData(): Flow<List<UserCityWeather>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUserCityWeather(userCityWeather: UserCityWeather)

    @Delete
    suspend fun deleteUserCityWeather(userCityWeather: UserCityWeather)

    @Query("SELECT * FROM user_cities_weather WHERE cityName LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<UserCityWeather>>

    @Insert
    suspend fun addCityModel(cityModel: CitiesModel)

    @Query("SELECT * FROM cities_Name_table")
    fun readAllCityData(): LiveData<List<CitiesModel>>

    @Query("SELECT * FROM cities_Name_table WHERE cityName LIKE :searchQuery")
    fun  citiesSearchDatabase(searchQuery: String): Flow<List<CitiesModel>>

}