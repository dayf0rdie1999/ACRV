package com.example.acrv.fragments

import android.os.Bundle
import android.util.Log.d
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.acrv.R
import com.example.acrv.databinding.FragmentUserWeatherForecastBinding
import com.example.acrv.fragments.UserCityWeatherForecast.UserCityWeatherAdapter
import com.example.acrv.repository.Repository
import com.example.acrv.viewmodel.MainViewModel
import com.example.acrv.viewmodel.UserCityWeatherViewModel
import com.example.acrv.viewmodelfactory.MainViewModelFactory

class userWeatherForecastFragment : Fragment(),SearchView.OnQueryTextListener {

    private lateinit var mUserCityWeatherViewModel: UserCityWeatherViewModel
    val userCityWeatherAdapter by lazy {
        UserCityWeatherAdapter()
    }

    val repositoy by lazy {
        Repository()
    }
    private lateinit var binding: FragmentUserWeatherForecastBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding =  FragmentUserWeatherForecastBinding.inflate(layoutInflater)

        // Adding the RecyclerView

        binding.userCityWeatherRv.adapter = userCityWeatherAdapter
        binding.userCityWeatherRv.layoutManager = LinearLayoutManager(requireContext())

        //Initializing the mUserCityWeatherViewModel
        mUserCityWeatherViewModel = ViewModelProvider(this).get(UserCityWeatherViewModel::class.java)

        mUserCityWeatherViewModel.readAllData.observe(requireActivity(), { list ->
           userCityWeatherAdapter.setData(list)
        })

        //Set True for options menu
        setHasOptionsMenu(true)



        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_user_city_weather,menu)

        val search = menu?.findItem(R.id.app_bar_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            searchDatabase(query)
        }
        return true
    }

    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"
        mUserCityWeatherViewModel.searchDatabase(searchQuery).observe(requireActivity(), { list ->
            list.let {
                userCityWeatherAdapter.setData(it)
            }
        })
    }


}