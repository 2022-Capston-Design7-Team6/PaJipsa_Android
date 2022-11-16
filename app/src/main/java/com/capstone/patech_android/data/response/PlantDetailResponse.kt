package com.capstone.patech_android.data.response

import com.google.gson.annotations.SerializedName

data class PlantDetailResponse(
    @SerializedName("graph_list")
    val graphList: List<Graph>,
    @SerializedName("plant")
    val plantInfo: PlantInfo,
    @SerializedName("time_line")
    val timeLineList: List<TimeLine>
)

data class Graph(
    val date: String,
    val event: Int,
    val length: Double,
    val size: Double,
    val weight: Double
)

data class TimeLine(
    val date: String,
    @SerializedName("event_water")
    val eventWater: Boolean,
    @SerializedName("event_harvest")
    val eventHarvest: Boolean,
    @SerializedName("event_chgpot")
    val eventTrowel: Boolean,
    val image: String,
    @SerializedName("pk")
    val id: Int,
    @SerializedName("text")
    val record: String
)