package com.capstone.patech_android.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.patech_android.data.model.TimelineData
import com.capstone.patech_android.databinding.ItemDetailTimelineBinding

class DetailTimelineAdapter :
    ListAdapter<TimelineData, DetailTimelineAdapter.TimelineViewHolder>(TimelineDiffUtil()) {

    inner class TimelineViewHolder(
        private val binding: ItemDetailTimelineBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: TimelineData) {
            binding.data = data
            binding.rvCategory.adapter = DetailCategoryAdapter(data.category)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineViewHolder {
        val binding: ItemDetailTimelineBinding =
            ItemDetailTimelineBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return TimelineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimelineViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    private class TimelineDiffUtil : DiffUtil.ItemCallback<TimelineData>() {
        override fun areItemsTheSame(oldItem: TimelineData, newItem: TimelineData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TimelineData, newItem: TimelineData): Boolean {
            return oldItem == newItem
        }
    }
}