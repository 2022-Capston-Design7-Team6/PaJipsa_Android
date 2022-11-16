package com.capstone.patech_android.data.response

import com.google.gson.annotations.SerializedName

data class PatechResponse(
    @SerializedName("list")
    val rankList: List<Rank>,
    @SerializedName("price")
    val priceList: List<Price>,
    val user: User,
    @SerializedName("patech_indicator")
    val patechValue: String,
)

data class Rank(
    val nickname: String,
    @SerializedName("pk")
    val id: Int,
    val rank: Int,
    @SerializedName("total_gain")
    val totalPrice: Int
)

data class Price(
    val date: String,
    val price: Int,
    @SerializedName("species")
    val category: Int
)

data class User(
    val nickname: String,
    @SerializedName("pk")
    val userId: Int,
    val rank: Int,
    @SerializedName("total_gain")
    val totalPrice: Int,
)