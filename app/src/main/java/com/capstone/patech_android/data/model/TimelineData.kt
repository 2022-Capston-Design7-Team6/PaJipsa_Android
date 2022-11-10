package com.capstone.patech_android.data.model

data class TimelineData(
    val id: Int,
    val date: String,
    val record: String? = "",
    val image: String? = null,
    val category: ArrayList<CategoryData>
)
