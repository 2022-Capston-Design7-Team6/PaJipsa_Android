package com.capstone.patech_android.ui.harvest.aftercamera

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.patech_android.data.model.PreviewData
import com.capstone.patech_android.databinding.ItemCameraPreviewBinding

class AfterCameraPreviewAdapter :
    ListAdapter<PreviewData, AfterCameraPreviewAdapter.PreviewViewHolder>(PreviewDiffUtil()) {

    inner class PreviewViewHolder(
        private val binding: ItemCameraPreviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PreviewData) {
            binding.data = data

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviewViewHolder {
        val binding: ItemCameraPreviewBinding =
            ItemCameraPreviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return PreviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PreviewViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    private class PreviewDiffUtil : DiffUtil.ItemCallback<PreviewData>() {
        override fun areItemsTheSame(oldItem: PreviewData, newItem: PreviewData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PreviewData, newItem: PreviewData): Boolean {
            return oldItem == newItem
        }
    }
}