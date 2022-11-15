package com.capstone.patech_android.data.response

import com.google.gson.annotations.SerializedName

data class PreviewResponse(
    @SerializedName("photolist")
    val photoList: List<PreviewImage>
)

data class PreviewImage(
    val image: String,
    @SerializedName("my_date")
    val date: PlantInfo
)