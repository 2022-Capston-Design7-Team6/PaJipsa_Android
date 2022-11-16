package com.capstone.patech_android.util.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.capstone.patech_android.R

@BindingAdapter("setCategoryImg22")
fun ImageView.bindCategoryImg22(category: Int) {
    when (category) {
        0 -> this.setImageResource(R.drawable.ic_green_onion_22)
        2 -> this.setImageResource(R.drawable.ic_onion_22)
        1 -> this.setImageResource(R.drawable.ic_chive_22)
        else -> this.setImageResource(R.drawable.ic_green_onion_22)
    }
}