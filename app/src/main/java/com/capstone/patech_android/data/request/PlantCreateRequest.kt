package com.capstone.patech_android.data.request

import com.google.gson.annotations.SerializedName

data class PlantCreateRequest(
    @SerializedName("plant_name")
    val plantName: String,
    @SerializedName("plant_species")
    val category: Int,
    @SerializedName("pot_size")
    val potSize: Int,
    @SerializedName("pot_ratio")
    val potRatio: Float,
)
