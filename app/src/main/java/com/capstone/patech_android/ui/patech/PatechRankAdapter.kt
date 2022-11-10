package com.capstone.patech_android.ui.patech

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.patech_android.data.model.PatechRankData
import com.capstone.patech_android.databinding.ItemPatechRankBinding

class PatechRankAdapter :
    ListAdapter<PatechRankData, PatechRankAdapter.PatechRankViewHolder>(PatechDiffUtil()) {

    inner class PatechRankViewHolder(
        private val binding: ItemPatechRankBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PatechRankData) {
            binding.data = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatechRankViewHolder {
        val binding: ItemPatechRankBinding =
            ItemPatechRankBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return PatechRankViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PatechRankViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    private class PatechDiffUtil : DiffUtil.ItemCallback<PatechRankData>() {
        override fun areItemsTheSame(oldItem: PatechRankData, newItem: PatechRankData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PatechRankData, newItem: PatechRankData): Boolean {
            return oldItem == newItem
        }
    }
}