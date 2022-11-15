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
    val event: Int?,
    val image: String,
    @SerializedName("pk")
    val id: Int,
    @SerializedName("text")
    val record: String
)