package com.capstone.patech_android.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.patech_android.data.response.HomePlantListData
import com.capstone.patech_android.databinding.ItemHomeListBinding
import com.capstone.patech_android.util.navigateWithData

class HomePlantListAdapter :
    ListAdapter<HomePlantListData, HomePlantListAdapter.HomePlantViewHolder>(PlantDiffUtil()) {

    inner class HomePlantViewHolder(
        private val binding: ItemHomeListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: HomePlantListData) {
            binding.data = data
            // TODO: harvestTime format 2022-11-14T10:07:48.943782Z
            binding.tvHarvest.text = data.plantInfo.harvestTime
            binding.root.setOnClickListener {
                it.navigateWithData(
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(data.id)
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePlantViewHolder {
        val binding: ItemHomeListBinding =
            ItemHomeListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return HomePlantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomePlantViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    private class PlantDiffUtil : DiffUtil.ItemCallback<HomePlantListData>() {
        override fun areItemsTheSame(
            oldItem: HomePlantListData,
            newItem: HomePlantListData
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: HomePlantListData,
            newItem: HomePlantListData
        ): Boolean {
            return oldItem == newItem
        }
    }
}