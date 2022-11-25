package com.capstone.patech_android.ui.detail

import android.util.Log
import androidx.lifecycle.*
import com.capstone.patech_android.data.api.ServiceBuilder
import com.capstone.patech_android.data.response.Graph
import com.capstone.patech_android.data.response.TimeLine
import com.capstone.patech_android.util.PairMediatorLiveData
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DetailViewModel : ViewModel() {

    val plantName = MutableLiveData("")
    val plantCategory = MutableLiveData(0)

    private val _plantHarvest = MutableLiveData<String?>()
    val plantHarvest: LiveData<String?> = _plantHarvest

    private val _birthDate = MutableLiveData<Int>()
    val birthDate: LiveData<Int> = _birthDate

    private val _startDate = MutableLiveData<String>()
    val startDate: LiveData<String> = _startDate

    val dateString = MediatorLiveData<String>().apply {
        addSource(
            PairMediatorLiveData(birthDate, startDate)
        ) { triple ->
            val birthDate = triple.first
            val startDate = triple.second
            if (birthDate != null && startDate != null) {
                this.value = "${startDate.replace("-", ".")} (D+${birthDate}일)"
            }
        }
    }

    private val _timelineList = MutableLiveData<List<TimeLine>>()
    val timelineList: LiveData<List<TimeLine>> = _timelineList

    private val _graphDataList = MutableLiveData<List<Graph>>()
    val graphDataList: LiveData<List<Graph>> = _graphDataList

    fun fetchDetailData(id: Int) {
        viewModelScope.launch {
            try {
                val response = ServiceBuilder.plantService.getPlantDetail(
                    id
                )
                _timelineList.postValue(response.timeLineList)
                _graphDataList.value = response.graphList.reversed()
                _plantHarvest.value = response.plantInfo.harvestTime
                _startDate.value = response.plantInfo.startDate
                _birthDate.value = response.plantInfo.birthDay
            } catch (e: HttpException) {
                _timelineList.postValue(listOf())
                Log.d("fetchDetailData", e.message().toString())
            }
        }
    }

    fun setPlantInfoDate(name: String, category: Int) {
        plantName.value = name
        plantCategory.value = category
    }
}