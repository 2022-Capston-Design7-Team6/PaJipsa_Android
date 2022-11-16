package com.capstone.patech_android.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.patech_android.data.model.PlantListData
import com.capstone.patech_android.databinding.ItemPlantListBinding

class PlantListAdapter :
    ListAdapter<PlantListData, PlantListAdapter.PaListViewHolder>(PlantDiffUtil()) {

    inner class PaListViewHolder(
        private val binding: ItemPlantListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PlantListData) {
            binding.data = data
            binding.root.setOnClickListener {
//                it.navigateWithData(
//                    PlantListFragmentDirections.actionPlantListFragmentToDetailFragment()
//                )
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