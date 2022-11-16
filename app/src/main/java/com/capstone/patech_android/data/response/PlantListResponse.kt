package com.capstone.patech_android.data.response

import com.google.gson.annotations.SerializedName

data class PlantListResponse(
    @SerializedName("plantlist")
    val plantList : List<PlantListData>
)
