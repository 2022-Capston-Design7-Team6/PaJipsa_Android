package com.capstone.patech_android.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentPlantListBinding
import com.capstone.patech_android.util.popBackStack

class PlantListFragment : ViewModelFragment<FragmentPlantListBinding, PlantListViewModel>(
    R.layout.fragment_plant_list
) {
    override val viewModel: PlantListViewModel by viewModels()

    private lateinit var plantListAdapter: PlantListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        plantListAdapter = PlantListAdapter()
        viewModel.fetchPlantList()
        initPlantRVAdapter()
        setPlantList()
        addListener()
    }

    private fun initPlantRVAdapter() {
        binding.rvList.adapter = plantListAdapter
    }

    private fun setPlantList() {
        viewModel.plantList.observe(viewLifecycleOwner) { list ->
            list?.let {
                with(plantListAdapter) { submitList(list) }
            }
        }
    }

    private fun addListener() {
        binding.btnBack.setOnClickListener {
            popBackStack()
        }
    }
}