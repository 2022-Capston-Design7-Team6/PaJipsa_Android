package com.capstone.patech_android.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.patech_android.data.api.ServiceBuilder
import com.capstone.patech_android.data.response.TimeLine
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DetailViewModel : ViewModel() {

    val plantName = MutableLiveData("")
    val plantCategory = MutableLiveData(0)

    private val _plantHarvest = MutableLiveData<String?>()
    val plantHarvest: LiveData<String?> = _plantHarvest

    private val _birthDate = MutableLiveData<Int>()
    val birthDate: LiveData<Int> = _birthDate

    private val _timelineList = MutableLiveData<List<TimeLine>>()
    val timelineList: LiveData<List<TimeLine>> = _timelineList

    fun fetchDetailData(plantId: Int) {
        viewModelScope.launch {
            try {
                val response = ServiceBuilder.plantService.getPlantDetail(
                    plantId
                )
                _timelineList.postValue(response.timeLineList)
                plantName.value = response.plantInfo.plantName
                plantCategory.value = response.plantInfo.plantCategory
                _plantHarvest.value = response.plantInfo.harvestTime
                _birthDate.value = response.plantInfo.birthDay
            } catch (e: HttpException) {
                _timelineList.postValue(listOf())
                Log.d("fetchDetailData", e.message().toString())
            }
        }
    }
}