package com.capstone.patech_android.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

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

@Parcelize
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
    val plantCategory: Int,
    @SerializedName("start_date")
    val startDate: String,
) : Parcelable