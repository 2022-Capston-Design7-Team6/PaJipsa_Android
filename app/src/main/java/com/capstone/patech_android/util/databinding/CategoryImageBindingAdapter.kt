package com.capstone.patech_android.util.databinding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.capstone.patech_android.R

@BindingAdapter("setCategoryImg22")
fun ImageView.bindCategoryImg22(category: Int) {
    when (category) {
        1 -> this.setImageResource(R.drawable.ic_green_onion_22)
        2 -> this.setImageResource(R.drawable.ic_onion_22)
        3 -> this.setImageResource(R.drawable.ic_chive_22)
        else -> this.setImageResource(R.drawable.ic_green_onion_22)
    }
}

@BindingAdapter("setEventText")
fun TextView.bindEventText(event: Int?) {
    when (event) {
        1 -> this.text = "물주기"
        2 -> this.text = "수확"
        3 -> this.text = "분갈이"
        else -> this.text = ""
    }
}