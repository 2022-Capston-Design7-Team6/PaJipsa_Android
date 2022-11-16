package com.capstone.patech_android.ui.home

import android.os.Bundle
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import androidx.fragment.app.viewModels
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentHomeBinding
import com.capstone.patech_android.util.navigate

class HomeFragment : ViewModelFragment<FragmentHomeBinding, HomeViewModel>(
    R.layout.fragment_home
) {
    override val viewModel: HomeViewModel by viewModels()

    private lateinit var plantAdapter: HomePlantListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        plantAdapter = HomePlantListAdapter()
        viewModel.fetchPlantList()
        initView()
        initHomeRVAdapter()
        setPlantList()
        addListener()
    }

    private fun initView() {
        viewModel.patechValue.observe(viewLifecycleOwner) {
            if (it.isNotBlank()) {
                binding.tvValue.text = buildSpannedString {
                    append(it)
                    inSpans(
                        ForegroundColorSpan(requireContext().getColor(R.color.black)),
                    ) {
                        append("만큼을 수확했어요!")
                    }
                }
            }
        }
    }

    private fun initHomeRVAdapter() {
        binding.rvPlants.adapter = plantAdapter
    }

    private fun setPlantList() {
        viewModel.plantList.observe(viewLifecycleOwner) { list ->
            list?.let {
                with(plantAdapter) { submitList(list) }
            }
        }
    }

    private fun addListener() {
        binding.layoutRank.setOnClickListener {
            navigate(R.id.action_homeFragment_to_patechFragment)
        }
        binding.icProfile.setOnClickListener {
            navigate(R.id.action_homeFragment_to_profileFragment)
        }
        binding.layoutListTitle.setOnClickListener {
            navigate(R.id.action_homeFragment_to_plantListFragment)
        }
//        binding.layoutHarvest.setOnClickListener {
//            navigate(R.id.action_homeFragment_to_harvestFragment)
//        }
    }
}