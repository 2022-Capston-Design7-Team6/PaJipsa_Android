package com.capstone.patech_android.ui.list

import android.util.Log
import androidx.lifecycle.*
import com.capstone.patech_android.data.api.ServiceBuilder
import com.capstone.patech_android.data.response.PlantListData
import com.capstone.patech_android.util.PairMediatorLiveData
import kotlinx.coroutines.launch
import retrofit2.HttpException

class PlantListViewModel : ViewModel() {

    private val _plantOriginList = MutableLiveData<List<PlantListData>>()
    val plantOriginList: LiveData<List<PlantListData>> = _plantOriginList

    private val _plantRVList = MutableLiveData<List<PlantListData>?>()
    val plantRVList: LiveData<List<PlantListData>?> = _plantRVList

    private val validServer = MutableLiveData(false)

    val showEmptyView = MediatorLiveData<Boolean>().apply {
        addSource(
            PairMediatorLiveData(plantRVList, validServer)
        ) { triple ->
            val plantRVList = triple.first
            val validServer = triple.second
            this.value = plantRVList == null && validServer == true
        }
    }

    fun fetchPlantList() {
        viewModelScope.launch {
            try {
                val response = ServiceBuilder.plantService.getPlantList()
                _plantOriginList.value = response.plantList
                _plantRVList.value = response.plantList
                validServer.value = true
            } catch (e: HttpException) {
                _plantOriginList.postValue(listOf())
                Log.d("getPlantList", e.message().toString())
            }
        }
    }

    fun setPlantRVList(filterCategory: Int? = null) {
        when (filterCategory) {
            null -> _plantRVList.value = plantOriginList.value
            else -> {
                val filteredList =
                    plantOriginList.value?.filter { it.plantInfo.plantCategory == filterCategory }
                _plantRVList.value = if (filteredList.isNullOrEmpty()) null else filteredList
            }
        }
    }
}