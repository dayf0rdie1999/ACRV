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
import com.example.acrv.repository.Repository
import com.example.acrv.repository.UserRepository
import com.example.acrv.viewmodel.MainViewModel
import com.example.acrv.viewmodel.UserCityWeatherViewModel
import com.example.acrv.viewmodelfactory.MainViewModelFactory


class UserCityDetailWeatherFragment : Fragment() {

    // Applying safeArgs to pass the data safely without leak
    private val args by navArgs<UserCityDetailWeatherFragmentArgs>()
    // declare without intializing the variables
    private lateinit var mMainViewModel: MainViewModel
    private lateinit var mMainViewModelFactory: MainViewModelFactory
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
        val view = inflater.inflate(R.layout.fragment_user_city_detail_weather, container, false)
        val cityName = view.findViewById<TextView>(R.id.userDetailCityName_tv)
        val cityWeather = view.findViewById<TextView>(R.id.userDetailCityWeather_tv)

        // Initializing
        mMainViewModelFactory = MainViewModelFactory(repository)
        mMainViewModel = ViewModelProvider(this,mMainViewModelFactory).get(MainViewModel::class.java)

        // Retrieving the data from the webserver
        mMainViewModel.getCityWeather(args.userCityWeatherData.cityName)

        mMainViewModel.myCityWeather.observe(viewLifecycleOwner,{ response ->
            if (response.isSuccessful) {
                cityName.text = response?.body()!!.name
                cityWeather.text = response?.body()!!.weather[0].main
            } else {
                cityName.text = response?.raw().toString()
                d("URL",response?.raw().toString() )
            }
        })

        //Telling the toolbar there will be a menu item
        setHasOptionsMenu(true)

        //Initializing UserCityWeatherViewModel
        mUserCityWeatherViewModel = ViewModelProvider(this).get(UserCityWeatherViewModel::class.java)

        return view
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
            R.id.cityDetailRemove_menuItem -> mUserCityWeatherViewModel.readAllData
                .observe(viewLifecycleOwner,{list ->
                    // Filtering the list which means loop and if conditions
                    list.filter {
                        it.cityName == args.userCityWeatherData.cityName
                    }
                            /*** Let is a higher order functions to allow to perform action with
                            // the element in the data ***/
                        .let {
                            mUserCityWeatherViewModel.deleteUserCityWeather(list[0])
                            findNavController().navigateUp()
                        }
                })

        }

        return super.onOptionsItemSelected(item)
    }
}