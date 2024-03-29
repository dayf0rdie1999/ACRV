package com.example.acrv.roomData

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.acrv.roomModel.CitiesModel
import com.example.acrv.roomModel.UserCityWeather


@Database(entities = [UserCityWeather::class, CitiesModel::class],version = 15,exportSchema = false)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        // Making this user database visible to the threads which is background
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                )
                        .fallbackToDestructiveMigration()
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}