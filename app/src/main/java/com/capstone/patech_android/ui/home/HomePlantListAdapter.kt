package com.capstone.patech_android.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.patech_android.data.model.PlantListData
import com.capstone.patech_android.databinding.ItemHomeListBinding
import com.capstone.patech_android.util.navigateWithData

class HomePlantListAdapter :
    ListAdapter<PlantListData, HomePlantListAdapter.HomePlantViewHolder>(FriendDiffUtil()) {

    inner class HomePlantViewHolder(
        private val binding: ItemHomeListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PlantListData) {
            binding.data = data
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

    private class FriendDiffUtil : DiffUtil.ItemCallback<PlantListData>() {
        override fun areItemsTheSame(oldItem: PlantListData, newItem: PlantListData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PlantListData, newItem: PlantListData): Boolean {
            return oldItem == newItem
        }
    }
}