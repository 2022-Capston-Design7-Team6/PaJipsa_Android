package com.capstone.patech_android.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.patech_android.data.response.PlantListData
import com.capstone.patech_android.databinding.ItemPlantListBinding
import com.capstone.patech_android.util.navigateWithData

class PlantListAdapter(private val viewModel: PlantListViewModel, private val fragment: PlantListFragment) :
    ListAdapter<PlantListData, PlantListAdapter.PaListViewHolder>(PlantDiffUtil()) {

    inner class PaListViewHolder(
        private val binding: ItemPlantListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PlantListData) {
            binding.data = data

            binding.root.setOnClickListener {
                it.navigateWithData(
                    PlantListFragmentDirections.actionPlantListFragmentToDetailFragment(
                        plantId = data.plantInfo.id,
                        plantName = data.plantInfo.plantName,
                        plantCategory = data.plantInfo.plantCategory
                    )
                )
            }

            viewModel.editMode.observe(fragment.viewLifecycleOwner) { isEditMode ->
                if (isEditMode) {
                    binding.cbList.visibility = View.VISIBLE
                    binding.root.isClickable = false
                } else {
                    binding.cbList.visibility = View.GONE
                    binding.cbList.isChecked = false
                    binding.root.isClickable = true
                }
            }

            binding.cbList.setOnCheckedChangeListener { _, _ ->
                viewModel.setDeleteItems(data.plantInfo.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaListViewHolder {
        val binding: ItemPlantListBinding =
            ItemPlantListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return PaListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PaListViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    private class PlantDiffUtil : DiffUtil.ItemCallback<PlantListData>() {
        override fun areItemsTheSame(oldItem: PlantListData, newItem: PlantListData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PlantListData, newItem: PlantListData): Boolean {
            return oldItem == newItem
        }
    }
}