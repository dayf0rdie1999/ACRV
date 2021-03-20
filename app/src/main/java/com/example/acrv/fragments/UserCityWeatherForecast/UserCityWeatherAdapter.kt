package com.example.acrv.fragments.UserCityWeatherForecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.acrv.databinding.UserCardLayoutRowBinding
import com.example.acrv.fragments.userWeatherForecastFragmentDirections
import com.example.acrv.roomModel.UserCityWeather
import com.example.acrv.util.UserCityDiffUtil

class UserCityWeatherAdapter: RecyclerView.Adapter<UserCityWeatherAdapter.myUserCityWeatherViewHolder>() {

    private var myUserCityWeatherList = emptyList<UserCityWeather>()

    class myUserCityWeatherViewHolder(val binding: UserCardLayoutRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myUserCityWeatherViewHolder {
       return myUserCityWeatherViewHolder(UserCardLayoutRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: myUserCityWeatherViewHolder, position: Int) {
        holder.binding.userCityWeatherNameTv.text = myUserCityWeatherList[position].cityName


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
        val diffResult = DiffUtil.calculateDiff(diffUtil);
        myUserCityWeatherList = newMyCityWeatherList

        diffResult.dispatchUpdatesTo(this)
    }
}