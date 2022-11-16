package com.capstone.patech_android.ui.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.patech_android.data.api.ServiceBuilder
import com.capstone.patech_android.data.response.PlantListData
import kotlinx.coroutines.launch
import retrofit2.HttpException

class PlantListViewModel : ViewModel() {

    private val _plantList = MutableLiveData<List<PlantListData>>()
    val plantList: LiveData<List<PlantListData>> = _plantList

    fun fetchPlantList() {
        viewModelScope.launch {
            try {
                val response = ServiceBuilder.plantService.getPlantList()
                _plantList.postValue(response.plantList)
            } catch (e: HttpException) {
                _plantList.postValue(listOf())
                Log.d("getPlantList", e.message().toString())
            }
        }
    }
}