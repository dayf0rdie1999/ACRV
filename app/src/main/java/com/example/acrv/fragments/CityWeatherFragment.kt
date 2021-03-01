package com.example.acrv.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.acrv.R
import com.example.acrv.repository.UserRepository
import com.example.acrv.roomModel.UserCityWeather
import com.example.acrv.viewmodel.UserCityWeatherViewModel
import kotlinx.android.synthetic.main.fragment_city_weather.*

class cityWeatherFragment : Fragment() {

    // Applying safeArgs to pass the data safely without leak
    private val args by navArgs<cityWeatherFragmentArgs>()

    private lateinit var mUserCityWeatherViewModel: UserCityWeatherViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_city_weather, container, false)

        // Intializing mUserCityWeatherViewModel
        mUserCityWeatherViewModel = ViewModelProvider(this).get(UserCityWeatherViewModel::class.java)

        // Binding
        val cityName = view.findViewById<TextView>(R.id.cityNameCityFragment_tv)
        val rain = view.findViewById<TextView>(R.id.rainCityFragment_tv)


        // Changing the text
        cityName.text = args.cityWeatherInformation.name

        if (args.cityWeatherInformation.rain == null) {
            rain.text = "No Rain"
        }else {
            rain.text = "Rain"
        }

        // Adding menu options on the toolbar
        setHasOptionsMenu(true)

        return view
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
                val userCityWeather = UserCityWeather(0, cityNameCityFragment_tv.text.toString())
                mUserCityWeatherViewModel.addUserCityWeather(userCityWeather)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}