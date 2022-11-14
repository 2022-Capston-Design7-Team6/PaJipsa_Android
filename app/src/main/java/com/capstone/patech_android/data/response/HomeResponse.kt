package com.capstone.patech_android.data.response

import com.google.gson.annotations.SerializedName

data class HomeResponse(
    val nickname: String,
    @SerializedName("patech_indicator")
    val patechValue: String,
    @SerializedName("img_list")
    val plantList: List<HomePlantListData>
)

data class HomePlantListData(
    @SerializedName("pk")
    val id: Int,
    val image: String,
    @SerializedName("plant")
    val plantInfo: PlantInfo
)

data class PlantInfo(
    @SerializedName("d_day")
    val birthDay: Int,
    @SerializedName("harvest_date")
    val harvestTime: String?,
    @SerializedName("pk")
    val id: Int,
    @SerializedName("plant_name")
    val plantName: String,
    @SerializedName("plant_species")
    val plantCategory: Int
)