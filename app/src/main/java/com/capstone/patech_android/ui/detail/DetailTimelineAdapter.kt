package com.capstone.patech_android.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.patech_android.data.response.TimeLine
import com.capstone.patech_android.databinding.ItemDetailTimelineBinding
import com.capstone.patech_android.util.timeFormatToCalender
import java.util.*

class DetailTimelineAdapter :
    ListAdapter<TimeLine, DetailTimelineAdapter.TimelineViewHolder>(TimelineDiffUtil()) {

    inner class TimelineViewHolder(
        private val binding: ItemDetailTimelineBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: TimeLine) {
            binding.data = data
            val time = timeFormatToCalender(data.date)
            if (time != null) {
                val month = time.get(Calendar.MONTH) + 1
                val date = time.get(Calendar.DATE)
                val string = "$month.$date"
                binding.tvDate.text = string
            }
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

    private class TimelineDiffUtil : DiffUtil.ItemCallback<TimeLine>() {
        override fun areItemsTheSame(oldItem: TimeLine, newItem: TimeLine): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TimeLine, newItem: TimeLine): Boolean {
            return oldItem == newItem
        }
    }
}