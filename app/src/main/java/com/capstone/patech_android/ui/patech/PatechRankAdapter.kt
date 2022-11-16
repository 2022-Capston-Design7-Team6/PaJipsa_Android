package com.capstone.patech_android.ui.patech

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.patech_android.data.response.Rank
import com.capstone.patech_android.databinding.ItemPatechRankBinding

class PatechRankAdapter :
    ListAdapter<Rank, PatechRankAdapter.PatechRankViewHolder>(PatechDiffUtil()) {

    inner class PatechRankViewHolder(
        private val binding: ItemPatechRankBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Rank) {
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

    private class PatechDiffUtil : DiffUtil.ItemCallback<Rank>() {
        override fun areItemsTheSame(oldItem: Rank, newItem: Rank): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Rank, newItem: Rank): Boolean {
            return oldItem == newItem
        }
    }
}