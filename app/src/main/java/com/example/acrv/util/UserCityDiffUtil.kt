package com.example.acrv.util

import androidx.recyclerview.widget.DiffUtil
import com.example.acrv.roomModel.UserCityWeather

class UserCityDiffUtil(val oldList: List<UserCityWeather>, val newList: List<UserCityWeather>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].cityName != newList[newItemPosition].cityName -> {
                false
            }
            oldList[oldItemPosition].rain != newList[newItemPosition].rain -> {
                false
            }
            else -> true
        }
    }
}