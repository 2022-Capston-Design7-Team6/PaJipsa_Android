package com.capstone.patech_android.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.patech_android.data.model.PlantListData
import com.capstone.patech_android.databinding.ItemPlantListBinding
import com.capstone.patech_android.util.navigateWithData

class PlantListAdapter :
    ListAdapter<PlantListData, PlantListAdapter.PaListViewHolder>(FriendDiffUtil()) {

    inner class PaListViewHolder(
        private val binding: ItemPlantListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PlantListData) {
            binding.data = data
            binding.root.setOnClickListener {
                it.navigateWithData(
                    PlantListFragmentDirections.actionPlantListFragmentToDetailFragment(data.id)
                )
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

    private class FriendDiffUtil : DiffUtil.ItemCallback<PlantListData>() {
        override fun areItemsTheSame(oldItem: PlantListData, newItem: PlantListData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PlantListData, newItem: PlantListData): Boolean {
            return oldItem == newItem
        }
    }
}