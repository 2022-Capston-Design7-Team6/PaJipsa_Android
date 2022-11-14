package com.capstone.patech_android.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.patech_android.data.api.ServiceBuilder
import com.capstone.patech_android.data.response.HomePlantListData
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HomeViewModel : ViewModel() {
    val nickname = MutableLiveData("")
    val patechValue = MutableLiveData("")

    private val _plantList = MutableLiveData<List<HomePlantListData>>()
    val plantList: LiveData<List<HomePlantListData>> = _plantList

    fun fetchPlantList() {
        viewModelScope.launch {
            try {
                val response = ServiceBuilder.homeService.getHome()
                _plantList.postValue(response.plantList)
                patechValue.value = response.patechValue
                nickname.value = response.nickname
            } catch (e: HttpException) {
                _plantList.postValue(listOf())
                Log.d("homeService", e.message().toString())
            }
        }
    }
}