package com.example.acrv.fragments.citiesweatherforecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.acrv.R
import com.example.acrv.databinding.CardLayoutRowBinding
import com.example.acrv.fragments.weatherForeCastFragmentDirections
import com.example.acrv.modelpackage.citiesmodel.CityWeather
import com.example.acrv.roomModel.CitiesModel
import com.example.acrv.util.CitiesModelDiffUtil
import com.example.acrv.util.UserCityDiffUtil

class CitiesWeatherAdapter: RecyclerView.Adapter<CitiesWeatherAdapter.MyCitiesWeatherViewHoler>() {

    private var myCitiesWeatherList = emptyList<CitiesModel>()

    inner class MyCitiesWeatherViewHoler(val binding: CardLayoutRowBinding): RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCitiesWeatherViewHoler {
        return MyCitiesWeatherViewHoler(CardLayoutRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyCitiesWeatherViewHoler, position: Int) {

        holder.binding.cityNameCardTv.text = myCitiesWeatherList[position].cityName
        holder.binding.cityNameCardTv.alpha = 1F

        if (myCitiesWeatherList[position].rain == "Rain"){
            holder.binding.cityRainCardIV.setImageResource(R.drawable.ic_icon_rain)
        } else {
            holder.binding.cityRainCardIV.setImageResource(R.drawable.ic_icon_sun)
        }


        holder.itemView.setOnClickListener {
            val action = weatherForeCastFragmentDirections.actionWeatherForeCastFragmentToCityWeatherFragment(myCitiesWeatherList[position])
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return myCitiesWeatherList.size
    }

    fun setData(newCityWeatherList: List<CitiesModel>) {
//        val diffUtil = CitiesModelDiffUtil(myCitiesWeatherList,newCityWeatherList)
//        val diffResult = DiffUtil.calculateDiff(diffUtil);
        myCitiesWeatherList = newCityWeatherList
        notifyDataSetChanged()
//        diffResult.dispatchUpdatesTo(this)
    }




}