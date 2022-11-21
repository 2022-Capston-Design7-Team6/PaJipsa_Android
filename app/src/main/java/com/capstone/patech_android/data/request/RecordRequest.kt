package com.capstone.patech_android.data.request

import com.google.gson.annotations.SerializedName

data class RecordRequest(
    val plant: Int,
    val image: String,
    val text: String,
    @SerializedName("event_water")
    val eventWater: Boolean,
    @SerializedName("event_chgpot")
    val eventTrowel: Boolean,
)
