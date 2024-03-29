package com.example.acrv.fragments

import android.os.Bundle
import android.util.Log.d
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.acrv.R
import com.example.acrv.databinding.FragmentUserCityDetailWeatherBinding
import com.example.acrv.repository.Repository
import com.example.acrv.repository.UserRepository
import com.example.acrv.viewmodel.MainViewModel
import com.example.acrv.viewmodel.UserCityWeatherViewModel
import com.example.acrv.viewmodelfactory.MainViewModelFactory


class UserCityDetailWeatherFragment : Fragment() {


    private lateinit var binding: FragmentUserCityDetailWeatherBinding
    // Applying safeArgs to pass the data safely without leak
    private val args by navArgs<UserCityDetailWeatherFragmentArgs>()

    // Declare and initialize using lazy high order functions
    val repository by lazy {
        Repository()
    }
    // declare without initializing the variable
    private lateinit var mUserCityWeatherViewModel: UserCityWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserCityDetailWeatherBinding.inflate(layoutInflater)


        binding.userCityNameCityFragmentTv.text = args.userCityWeatherData.cityName
        binding.userCurrentTempTV.text = args.userCityWeatherData.temp.toString()
        binding.userHumidityTV.text = args.userCityWeatherData.humidity.toString()
        binding.userMaxTempTV.text = args.userCityWeatherData.max_temp.toString()
        binding.userMinTempTV.text = args.userCityWeatherData.min_temp.toString()
        binding.userWindSpeedCityTV.text = args.userCityWeatherData.wind.toString()
        when(args.userCityWeatherData.weather) {
            ("Clear") -> {
                binding.userCityWeatherConditionIconIV.setImageResource(R.drawable.ic_icon_sun)
            }
            ("Clouds") -> {
                binding.userCityWeatherConditionIconIV.setImageResource(R.drawable.ic_icon_cloud)
            }
            else -> {
                binding.userCityWeatherConditionIconIV.setImageResource(R.drawable.ic_icon_rain)
            }
        }


        //Telling the toolbar there will be a menu item
        setHasOptionsMenu(true)

        //Initializing UserCityWeatherViewModel
        mUserCityWeatherViewModel = ViewModelProvider(this).get(UserCityWeatherViewModel::class.java)

        return binding.root
    }

    // Overriding the function of creating the optionsbar to manually input the menu created
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.user_city_detail_weather_fragment_menu,menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    // Overriding the function of the OptionsBar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Applying When Case to identify which item is selected
        when(item.itemId){
            // Observing the data in the background method
            R.id.cityDetailRemove_menuItem -> deleteUserCityWeather()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteUserCityWeather() {
        mUserCityWeatherViewModel.readAllData
            .observe(viewLifecycleOwner,{list ->
                // Filtering the list which means loop and if conditions
                list.filter {
                    it.cityName == args.userCityWeatherData.cityName
                }
                    /*** Let is a higher order functions to allow to perform action with
                    // the element in the data ***/
                    .let {
                        mUserCityWeatherViewModel.deleteUserCityWeather(it[0])
                        findNavController().navigateUp()
                    }
            })
    }
}