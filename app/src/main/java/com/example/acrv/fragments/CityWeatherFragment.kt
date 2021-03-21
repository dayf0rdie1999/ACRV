package com.example.acrv.fragments

import android.os.Bundle
import android.util.Log.d
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.acrv.R
import com.example.acrv.databinding.FragmentCityWeatherBinding
import com.example.acrv.repository.UserRepository
import com.example.acrv.roomModel.UserCityWeather
import com.example.acrv.viewmodel.UserCityWeatherViewModel
import kotlinx.android.synthetic.main.fragment_city_weather.*

class cityWeatherFragment : Fragment() {

    private lateinit var binding: FragmentCityWeatherBinding

    // Applying safeArgs to pass the data safely without leak
    private val args by navArgs<cityWeatherFragmentArgs>()

    private lateinit var mUserCityWeatherViewModel: UserCityWeatherViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentCityWeatherBinding.inflate(layoutInflater)

        // Intializing mUserCityWeatherViewModel
        mUserCityWeatherViewModel = ViewModelProvider(this).get(UserCityWeatherViewModel::class.java)


        binding.cityNameCityFragmentTv.text = args.cityWeatherInformation.cityName
        binding.currentTempTV.text = args.cityWeatherInformation.temp.toString()
        binding.humidityTV.text = args.cityWeatherInformation.humidity.toString()
        binding.maxTempTV.text = args.cityWeatherInformation.max_temp.toString()
        binding.minTempTV.text = args.cityWeatherInformation.min_temp.toString()
        binding.windSpeedCityTV.text = args.cityWeatherInformation.wind.toString()
        when(args.cityWeatherInformation.weather) {
            ("Clear") -> {
                binding.cityWeatherConditionIconIV.setImageResource(R.drawable.ic_icon_sun)
            }
            ("Clouds") -> {
                binding.cityWeatherConditionIconIV.setImageResource(R.drawable.ic_icon_cloud)
            }
            else -> {
                binding.cityWeatherConditionIconIV.setImageResource(R.drawable.ic_icon_rain)
            }
        }

        // Changing the text

        // Adding menu options on the toolbar
        setHasOptionsMenu(true)

        return binding.root
    }

    // Override function to create the menu options bar
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_user_city_weather,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    // Overriding the function to trigger action when is clicked on the menuItem
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.addUserCityWeather -> {
                checkRepeatedCity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // Checking whether the data already contains the cityName
    private fun checkRepeatedCity(){
        // Observing the data in the background
        mUserCityWeatherViewModel.readAllData.observe(viewLifecycleOwner, { list ->
            // Checking if the list is empty, it it is, we add the first item.
            if (list.isNotEmpty()) {
                // Find the object that that  has the same name
                var city  = list.find{it.cityName == cityNameCityFragment_tv.text}
                // if it is null add to it
                if (city == null) addingCityToUserDatabase()
            } else addingCityToUserDatabase()
        })
    }

    private fun addingCityToUserDatabase() {
        var userCityWeather = UserCityWeather(0, cityNameCityFragment_tv.text.toString(),
            rain = args.cityWeatherInformation.rain,
            weather = args.cityWeatherInformation.weather,
            max_temp = args.cityWeatherInformation.max_temp,
            min_temp = args.cityWeatherInformation.min_temp,
            temp = args.cityWeatherInformation.temp,
            humidity = args.cityWeatherInformation.humidity,
            wind = args.cityWeatherInformation.wind
            )
        // Checking if the city is already existed in the user list
        mUserCityWeatherViewModel.addUserCityWeather(userCityWeather)
    }
}