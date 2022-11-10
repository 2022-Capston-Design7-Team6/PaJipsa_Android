package com.capstone.patech_android.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.patech_android.data.model.PlantListData
import com.capstone.patech_android.databinding.ItemHomeListBinding

class HomePlantListAdapter :
    ListAdapter<PlantListData, HomePlantListAdapter.OtherProfileViewHolder>(FriendDiffUtil()) {

    inner class OtherProfileViewHolder(
        private val binding: ItemHomeListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PlantListData) {
            binding.data = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtherProfileViewHolder {
        val binding: ItemHomeListBinding =
            ItemHomeListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return OtherProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OtherProfileViewHolder, position: Int) {
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