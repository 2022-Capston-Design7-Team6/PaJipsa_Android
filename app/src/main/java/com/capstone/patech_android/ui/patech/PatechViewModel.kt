package com.capstone.patech_android.ui.patech

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.patech_android.data.model.PatechRankData

class PatechViewModel : ViewModel() {

    private val _rankList = MutableLiveData<List<PatechRankData>>()
    val rankList: LiveData<List<PatechRankData>> = _rankList

    fun fetchRankList() {
        _rankList.value = listOf(
            PatechRankData(rank = 1, nickname = "양파1", price = 14000),
            PatechRankData(rank = 2, nickname = "양파2", price = 5000),
            PatechRankData(rank = 3, nickname = "양파3", price = 3000),
            PatechRankData(rank = 4, nickname = "양파4", price = 2000),
        )
    }
}