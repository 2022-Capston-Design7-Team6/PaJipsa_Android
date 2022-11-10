package com.capstone.patech_android.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.patech_android.data.model.CategoryData
import com.capstone.patech_android.databinding.ItemDetailTimelineCategoryBinding

class DetailCategoryAdapter(private var itemList: MutableList<CategoryData>) :
    RecyclerView.Adapter<DetailCategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemDetailTimelineCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class CategoryViewHolder(val binding: ItemDetailTimelineCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoryData) {
            binding.data = item
        }
    }
}