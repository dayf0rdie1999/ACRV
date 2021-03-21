package com.example.acrv.util

import androidx.recyclerview.widget.DiffUtil
import com.example.acrv.roomModel.CitiesModel
import com.example.acrv.roomModel.UserCityWeather

class CitiesModelDiffUtil(val oldList: List<CitiesModel>, val newList: List<CitiesModel>): DiffUtil.Callback()
{
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (oldList[oldItemPosition].cityId == newList[newItemPosition].cityId)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].cityName != newList[newItemPosition].cityName -> {
                false
            }
            oldList[oldItemPosition].rain != oldList[newItemPosition].rain -> {
                false
            }
            else -> {
                true
            }
        }
    }
}