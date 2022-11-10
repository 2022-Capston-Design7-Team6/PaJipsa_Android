package com.capstone.patech_android.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.patech_android.data.model.PlantListData

class HomeViewModel : ViewModel() {

    private val _plantList = MutableLiveData<List<PlantListData>>()
    val plantList: LiveData<List<PlantListData>> = _plantList

    fun fetchPlantList() {
        _plantList.value = listOf(
            PlantListData(1, name = "양파1", harvestTime = "2주후 수확 예정", date = "D+10"),
            PlantListData(2, name = "양파2",harvestTime = "2주후 수확 예정", date = "D+10"),
            PlantListData(3, name = "양파3",harvestTime = "2주후 수확 예정", date = "D+10"),
            PlantListData(4, name = "양파4",harvestTime = "2주후 수확 예정", date = "D+10"),
        )
    }
}