package com.altintasomer.application.accuweathercase.view

import android.app.SearchManager
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.provider.SearchRecentSuggestions

import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.altintasomer.application.accuweathercase.R
import com.altintasomer.application.accuweathercase.data.network.model.WeatherCondition
import com.altintasomer.application.accuweathercase.data.network.model.local.SearchingItem
import com.altintasomer.application.accuweathercase.databinding.FragmentListBinding
import com.altintasomer.application.accuweathercase.view.adapter.SearchingListAdapter
import com.altintasomer.application.accuweathercase.viewmodel.GeneralViewModelModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.collections.ArrayList

private const val TAG = "ListFragment"

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {
    private val viewModel: GeneralViewModelModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    private fun init(view: View) {

        val binding = FragmentListBinding.bind(view)

        val adapter =
            SearchingListAdapter(ArrayList(listOf()), object : SearchingListAdapter.OnItemClickListener {
                override fun onItemClick(searchingItem: SearchingItem?) {
                    viewModel.getWeatherCondition(key = searchingItem?.key?:"")
                    val action = ListFragmentDirections.actionListFragmentToDetailFragment(searchingItem?.name?:"")
                    findNavController().navigate(action)
                }
            })

        binding.rvSearched.also {
            it.adapter = adapter
            val span =
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 1 else 2
            it.layoutManager = GridLayoutManager(context, span, GridLayoutManager.VERTICAL, false)
        }
        viewModel.searchItemList.observe(viewLifecycleOwner, { list ->
            adapter.updateList(list)

        })

        viewModel.searchQuery.observe(viewLifecycleOwner,{
            viewModel.search(it)
        })

        viewModel.error.observe(viewLifecycleOwner,{
            if (it)
                Toast.makeText(requireContext(),"Error!",Toast.LENGTH_LONG).show()
        })

    }
}