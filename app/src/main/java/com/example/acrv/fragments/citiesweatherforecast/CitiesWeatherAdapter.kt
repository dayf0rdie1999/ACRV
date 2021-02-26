package com.example.acrv.fragments.citiesweatherforecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.acrv.R
import com.example.acrv.fragments.weatherForeCastFragmentDirections
import com.example.acrv.modelpackage.citiesmodel.CityWeather

class CitiesWeatherAdapter: RecyclerView.Adapter<CitiesWeatherAdapter.MyCitiesWeatherViewHoler>() {

    private var myCitiesWeatherList = emptyList<CityWeather>()


    inner class MyCitiesWeatherViewHoler(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemCityNameCard: TextView
        var itemRainCard: TextView

        init {
            itemCityNameCard = itemView.findViewById<TextView>(R.id.cityNameCard_tv)
            itemRainCard = itemView.findViewById<TextView>(R.id.rainCard_tv)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCitiesWeatherViewHoler {
        return MyCitiesWeatherViewHoler(LayoutInflater.from(parent.context).inflate(R.layout.card_layout_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyCitiesWeatherViewHoler, position: Int) {

        holder.itemCityNameCard.text = myCitiesWeatherList[position].name.toString()
        if (myCitiesWeatherList[position].rain == null){
            holder.itemRainCard.text = "No Rain"
        } else {
            holder.itemRainCard.text = "Rain"
        }


        holder.itemView.setOnClickListener {
            val action = weatherForeCastFragmentDirections.actionWeatherForeCastFragmentToCityWeatherFragment(myCitiesWeatherList[position])
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return myCitiesWeatherList.size
    }

    fun setData(newList: List<CityWeather>) {
        myCitiesWeatherList = newList;
        notifyDataSetChanged()
    }




}