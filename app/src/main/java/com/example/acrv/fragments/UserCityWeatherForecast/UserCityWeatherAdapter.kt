package com.example.acrv.fragments.UserCityWeatherForecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.acrv.R
import com.example.acrv.databinding.CardLayoutRowBinding
import com.example.acrv.databinding.UserCardLayoutRowBinding
import com.example.acrv.fragments.userWeatherForecastFragmentDirections
import com.example.acrv.roomModel.UserCityWeather
import com.example.acrv.util.UserCityDiffUtil

class UserCityWeatherAdapter: RecyclerView.Adapter<UserCityWeatherAdapter.myUserCityWeatherViewHolder>() {

    private var myUserCityWeatherList = emptyList<UserCityWeather>()

    class myUserCityWeatherViewHolder(val binding: CardLayoutRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myUserCityWeatherViewHolder {
       return myUserCityWeatherViewHolder(CardLayoutRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: myUserCityWeatherViewHolder, position: Int) {
        holder.binding.cityNameCardTv.text = myUserCityWeatherList[position].cityName
        holder.binding.cityNameCardTv.alpha = 1F

        if (myUserCityWeatherList[position].rain == "Rain"){
            holder.binding.cityRainCardIV.setImageResource(R.drawable.ic_icon_rain)
        } else {
            holder.binding.cityRainCardIV.setImageResource(R.drawable.ic_icon_sun)
        }

        holder.binding.root.setOnClickListener {
            val action = userWeatherForecastFragmentDirections.actionUserWeatherForecastFragmentToUserCityDetailWeatherFragment(myUserCityWeatherList[position])
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return myUserCityWeatherList.size
    }

    fun setData(newMyCityWeatherList: List<UserCityWeather>){
        val diffUtil = UserCityDiffUtil(myUserCityWeatherList,newMyCityWeatherList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        myUserCityWeatherList = newMyCityWeatherList

        diffResult.dispatchUpdatesTo(this)
    }
}