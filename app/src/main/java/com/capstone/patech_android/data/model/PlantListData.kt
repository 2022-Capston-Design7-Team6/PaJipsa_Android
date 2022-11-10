package com.capstone.patech_android.data.model

data class PlantListData(
    val id: Int,
    val image: String? = "",
    val name: String,
    val harvestTime: String,
    val date: String,
)