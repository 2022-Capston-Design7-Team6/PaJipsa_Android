package com.capstone.patech_android.util.databinding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.capstone.patech_android.R

@BindingAdapter("isAbleOrUnable")
fun TextView.bindTextAbleOrUnable(isAble: Boolean) {
    when (isAble) {
        true -> {
            this.isClickable = true
            this.apply { setTextColor(resources.getColor(R.color.green_dark, null)) }
        }
        else -> {
            this.isClickable = false
            this.apply { setTextColor(resources.getColor(R.color.black_sub, null)) }
        }
    }
}