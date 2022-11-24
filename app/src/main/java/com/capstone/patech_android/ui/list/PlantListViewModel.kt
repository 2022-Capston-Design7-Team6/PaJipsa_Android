package com.capstone.patech_android.ui.list

import android.util.Log
import androidx.lifecycle.*
import com.capstone.patech_android.data.api.ServiceBuilder
import com.capstone.patech_android.data.response.PlantListData
import com.capstone.patech_android.util.PairMediatorLiveData
import kotlinx.coroutines.launch
import retrofit2.HttpException

class PlantListViewModel : ViewModel() {

    val editMode = MutableLiveData(false)

    private val _plantOriginList = MutableLiveData<List<PlantListData>>()
    val plantOriginList: LiveData<List<PlantListData>> = _plantOriginList

    private val _plantRVList = MutableLiveData<List<PlantListData>?>()
    val plantRVList: LiveData<List<PlantListData>?> = _plantRVList

    private val _deleteItems = MutableLiveData<Set<Int>?>()
    val deleteItems: LiveData<Set<Int>?> = _deleteItems

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

    fun setDeleteItems(plantId: Int) {
        val deleteItems = deleteItems.value.orEmpty()
        val currentItems = deleteItems.toHashSet().apply {
            if (this.contains(plantId)) {
                this.remove(plantId)
            } else {
                this.add(plantId)
            }
        }
        _deleteItems.value = currentItems
    }

    fun resetDeleteItems() {
        _deleteItems.value = null
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

    private val _successDelete = MutableLiveData<Boolean>()
    val successDelete: LiveData<Boolean> = _successDelete

    fun deletePlant() {
        viewModelScope.launch {
            try {
                for (plantId in deleteItems.value.orEmpty()) {
                    val response = ServiceBuilder.plantService.deletePlant(plantId)
                    if (response.code() == 204) {
                        _successDelete.value = true
                    }
                }
            } catch (e: HttpException) {
                Log.d("deletePlant", e.message().toString())
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