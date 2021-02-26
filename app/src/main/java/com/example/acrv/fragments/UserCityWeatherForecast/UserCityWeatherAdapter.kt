package com.example.acrv.fragments.UserCityWeatherForecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.acrv.R
import com.example.acrv.roomModel.UserCityWeather

class UserCityWeatherAdapter: RecyclerView.Adapter<UserCityWeatherAdapter.myUserCityWeatherViewHolder>() {

    private var myUserCityWeatherList = emptyList<UserCityWeather>()

    class myUserCityWeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemCityNameUserCard: TextView

        init {
            itemCityNameUserCard = itemView.findViewById(R.id.userCityWeatherName_tv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myUserCityWeatherViewHolder {
       return myUserCityWeatherViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_card_layout_row,parent,false))
    }

    override fun onBindViewHolder(holder: myUserCityWeatherViewHolder, position: Int) {
        holder.itemCityNameUserCard.text = myUserCityWeatherList[position].cityName

    }

    override fun getItemCount(): Int {
        return myUserCityWeatherList.size
    }

    fun setData(myCityWeatherList: List<UserCityWeather>){
        myUserCityWeatherList = myCityWeatherList
        notifyDataSetChanged()
    }
}