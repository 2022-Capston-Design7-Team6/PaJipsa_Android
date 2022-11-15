package com.capstone.patech_android.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.patech_android.data.response.HomePlantListData
import com.capstone.patech_android.databinding.ItemHomeListBinding
import com.capstone.patech_android.util.navigateWithData
import com.capstone.patech_android.util.timeFormatToCalender
import java.util.*

class HomePlantListAdapter :
    ListAdapter<HomePlantListData, HomePlantListAdapter.HomePlantViewHolder>(PlantDiffUtil()) {

    inner class HomePlantViewHolder(
        private val binding: ItemHomeListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: HomePlantListData) {
            binding.data = data
            val harvestTime = data.plantInfo.harvestTime
            if(harvestTime != null) {
                val time = timeFormatToCalender(harvestTime)
                if (time != null) {
                    val month = time.get(Calendar.MONTH) + 1
                    val date = time.get(Calendar.DATE)
                    val string = "수확 예정일 $month.$date"
                    binding.tvHarvest.text = string
                }
            }
            binding.root.setOnClickListener {
                it.navigateWithData(
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(data.plantInfo.id)
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