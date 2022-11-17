package com.capstone.patech_android.ui.harvest.aftercamera

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.patech_android.data.response.PreviewImage
import com.capstone.patech_android.databinding.ItemCameraPreviewBinding
import com.capstone.patech_android.ui.harvest.HarvestViewModel
import com.capstone.patech_android.util.timeFormatToPreviewDate

class AfterCameraPreviewAdapter(private val viewModel: HarvestViewModel) :
    ListAdapter<PreviewImage, AfterCameraPreviewAdapter.PreviewViewHolder>(PreviewDiffUtil()) {

    inner class PreviewViewHolder(
        private val binding: ItemCameraPreviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PreviewImage) {
            binding.data = data
            binding.tvDate.text = timeFormatToPreviewDate(data.date)

            binding.root.setOnClickListener {
                viewModel.setOverlapImage(data.image)
            }
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

    private class PreviewDiffUtil : DiffUtil.ItemCallback<PreviewImage>() {
        override fun areItemsTheSame(oldItem: PreviewImage, newItem: PreviewImage): Boolean {
            return oldItem.image == newItem.image
        }

        override fun areContentsTheSame(oldItem: PreviewImage, newItem: PreviewImage): Boolean {
            return oldItem == newItem
        }
    }
}