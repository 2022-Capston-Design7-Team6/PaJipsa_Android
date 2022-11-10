package com.capstone.patech_android.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.patech_android.data.model.CategoryData
import com.capstone.patech_android.data.model.TimelineData

class DetailViewModel : ViewModel() {

    private val _timelineList = MutableLiveData<List<TimelineData>>()
    val timelineList: LiveData<List<TimelineData>> = _timelineList

    fun fetchTimelineList() {
        _timelineList.value = listOf(
            TimelineData(
                1, record = "양파1", date = "D+10",
                category = arrayListOf(CategoryData("분갈이")),
            ),
            TimelineData(
                2, record = "양파2", date = "D+10",
                category = arrayListOf(CategoryData("분갈이"), CategoryData("수확"))
            ),
            TimelineData(
                3, record = "양파3", date = "D+10",
                category = arrayListOf(CategoryData("분갈이"), CategoryData("물주기")),
            ),
            TimelineData(
                4, record = "양파4", date = "D+10",
                category = arrayListOf(CategoryData("분갈이")),
            ),
        )
    }
}