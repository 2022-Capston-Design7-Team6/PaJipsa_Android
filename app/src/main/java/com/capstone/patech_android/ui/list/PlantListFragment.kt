package com.capstone.patech_android.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentPlantListBinding
import com.capstone.patech_android.ui.dialog.TwoButtonDialog
import com.capstone.patech_android.util.navigate
import com.capstone.patech_android.util.popBackStack

class PlantListFragment : ViewModelFragment<FragmentPlantListBinding, PlantListViewModel>(
    R.layout.fragment_plant_list
) {
    override val viewModel: PlantListViewModel by viewModels()

    private lateinit var plantListAdapter: PlantListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        plantListAdapter = PlantListAdapter(viewModel, this)
        viewModel.fetchPlantList()
        initPlantRVAdapter()
        setPlantList()
        addListener()
        setCategoryFilterChangeList()
        setEditMode()
        setEditModeObserve()
    }

    private fun initPlantRVAdapter() {
        binding.rvList.adapter = plantListAdapter
    }

    private fun setPlantList() {
        viewModel.plantRVList.observe(viewLifecycleOwner) { list ->
            list?.let {
                with(plantListAdapter) { submitList(list) }
            }
        }
    }

    private fun setCategoryFilterChangeList() {
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radio_all -> viewModel.setPlantRVList()
                R.id.radio_green_onion -> viewModel.setPlantRVList(0)
                R.id.radio_chive -> viewModel.setPlantRVList(1)
                R.id.radio_onion -> viewModel.setPlantRVList(2)
            }
        }
    }

    private fun addListener() {
        binding.btnBack.setOnClickListener {
            popBackStack()
        }
        binding.btnNew.setOnClickListener {
            navigate(R.id.action_plantListFragment_to_create_nav_graph)
        }
    }

    private fun setEditMode() {
        binding.btnEdit.setOnClickListener {
            viewModel.editMode.value = true
        }
        binding.tvCancel.setOnClickListener {
            viewModel.editMode.value = false
            viewModel.resetDeleteItems()
        }

        binding.tvDelete.setOnClickListener {
            TwoButtonDialog(0) {
                viewModel.deletePlant()
            }.show(childFragmentManager, "PLANT_DELETE")
        }
    }

    private fun setEditModeObserve() {
        viewModel.editMode.observe(viewLifecycleOwner) { editMode ->
            if (editMode) {
                binding.layoutDefaultBtn.visibility = View.GONE
                binding.layoutEditMode.visibility = View.VISIBLE
            } else {
                binding.layoutDefaultBtn.visibility = View.VISIBLE
                binding.layoutEditMode.visibility = View.GONE
            }
        }
    }
}