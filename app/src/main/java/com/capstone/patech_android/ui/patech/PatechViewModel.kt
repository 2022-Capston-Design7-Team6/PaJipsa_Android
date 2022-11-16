package com.capstone.patech_android.ui.patech

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.patech_android.data.api.ServiceBuilder
import com.capstone.patech_android.data.response.Rank
import kotlinx.coroutines.launch
import retrofit2.HttpException

class PatechViewModel : ViewModel() {

    private val _rankList = MutableLiveData<List<Rank>>()
    val rankList: LiveData<List<Rank>> = _rankList

    val userName = MutableLiveData("")
    val userRank = MutableLiveData(0)
    val userTotal = MutableLiveData(0)
    val patechValue = MutableLiveData("")

    private val _greenOnionPrice = MutableLiveData<Int?>()
    val greenOnionPrice: LiveData<Int?> = _greenOnionPrice

    private val _greenOnionPriceDate = MutableLiveData<String?>()
    val greenOnionPriceDate: LiveData<String?> = _greenOnionPriceDate

    fun fetchRankData() {
        viewModelScope.launch {
            try {
                val response = ServiceBuilder.homeService.getPatechInfo()
                _rankList.postValue(response.rankList)
                val user = response.user
                userName.value = user.nickname
                userRank.value = user.rank
                userTotal.value = user.totalPrice
                patechValue.value = response.patechValue
                _greenOnionPrice.value = response.priceList.find { it.category == 0 }?.price
                _greenOnionPriceDate.value =
                    response.priceList.find { it.category == 0 }?.date?.replace("-", ".")
            } catch (e: HttpException) {
                Log.d("getPatechInfo", e.message().toString())
            }
        }
    }
}