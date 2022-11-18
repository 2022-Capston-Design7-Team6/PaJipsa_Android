package com.capstone.patech_android.util.databinding

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import com.capstone.patech_android.R

@BindingAdapter("imageCoil")
fun ImageView.imageCoil(url: String?) {
    url?.let {
        load(url) {
            placeholder(R.color.gray_light)
            transformations(RoundedCornersTransformation(radius = 20F))
        }
    }
}

@BindingAdapter("imageCoil")
fun ImageView.imageCoil(url: Int?) {
    url?.let {
        load(url) {
            placeholder(R.color.gray_light)
            transformations(RoundedCornersTransformation(radius = 20F))
        }
    }
}

@BindingAdapter("imageCoil")
fun ImageView.imageCoil(url: Uri?) {
    url?.let {
        load(url) {
            placeholder(R.color.gray_light)
            transformations(RoundedCornersTransformation(radius = 20F))
        }
    }
}

@BindingAdapter("imageCoilNoRounded")
fun ImageView.imageCoilNoRounded(url: String?) {
    url?.let {
        load(url) {
            placeholder(R.color.gray_light)
        }
    }
}

@BindingAdapter("imageCoilNoRounded")
fun ImageView.imageCoilNoRounded(url: Uri?) {
    url?.let {
        load(url) {
            placeholder(R.color.gray_light)
        }
    }
}