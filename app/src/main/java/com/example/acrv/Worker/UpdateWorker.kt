package com.example.acrv.Worker

import android.content.Context
import android.util.Log.d
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.acrv.repository.Repository
import com.example.acrv.repository.UserRepository
import com.example.acrv.roomData.UserDatabase
import com.example.acrv.roomModel.CitiesModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateWorker(cxt: Context,params: WorkerParameters ):Worker(cxt,params){


    override fun doWork(): Result {




        // Initializing the userDao and userRepository
        val userDao = UserDatabase.getDatabase(applicationContext).userDao()
        val userRepository = UserRepository(userDao);

        // Initializing the Retrofit Repository
        val repository = Repository();

        GlobalScope.launch {
            val citiesWeatherResponse = repository.newGetCitiesWeather()
            if (citiesWeatherResponse.isSuccessful) {
                citiesWeatherResponse.body()?.list!!.forEach { city ->
                    if (city.rain == null) {
                        var it = CitiesModel(0,
                            cityId = city.id,
                            cityName =  city.name,
                            rain = "No Rain",
                            weather = city.weather[0].main,
                            max_temp = city.main.temp_max,
                            min_temp = city.main.temp_min,
                            temp = city.main.temp,
                            humidity = city.main.humidity,
                            wind = city.wind.speed
                        )
                        userRepository.updateDatabase(it)
                        userRepository.updateUserCityWeather("No Rain",city.weather[0].main,city.main.temp_max,city.main.temp_min,city.main.temp,city.main.humidity,city.wind.speed,city.name)
                    } else {
                        var it = CitiesModel(0,
                            cityId = city.id,
                            cityName =  city.name,
                            rain = "Rain",
                            weather = city.weather[0].main,
                            max_temp = city.main.temp_max,
                            min_temp = city.main.temp_min,
                            temp = city.main.temp,
                            humidity = city.main.humidity,
                            wind = city.wind.speed
                        )
                        userRepository.updateDatabase(it)
                        userRepository.updateUserCityWeather("Rain",city.weather[0].main,city.main.temp_max,city.main.temp_min,city.main.temp,city.main.humidity,city.wind.speed,city.name)
                    }
                }
                d("Success","Data and UserData is Updated")
            } else {
                d("Fail","Data and UserData is not Updated")
            }
        }


        return  Result.success()
    }
}