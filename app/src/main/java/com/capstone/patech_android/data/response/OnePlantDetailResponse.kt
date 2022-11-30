package com.capstone.patech_android.data.response

import com.google.gson.annotations.SerializedName

data class OnePlantDetailResponse(
    @SerializedName("beforeimage")
    val beforeImage: String?,
    val date: String,
    @SerializedName("event_chgpot")
    val eventTrowel: Boolean,
    @SerializedName("event_harvest")
    val eventHarvest: Boolean,
    @SerializedName("event_water")
    val eventWater: Boolean,
    val id: Int,
    val image: String,
    @SerializedName("plant")
    val plantId: Int,
    @SerializedName("text")
    val record: String,
)