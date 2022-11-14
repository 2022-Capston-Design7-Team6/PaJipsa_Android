package com.capstone.patech_android.ui.record

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.patech_android.R
import com.capstone.patech_android.data.model.PreviewData

class RecordViewModel : ViewModel() {

    private val _previewList = MutableLiveData<List<PreviewData>>()
    val previewList: LiveData<List<PreviewData>> = _previewList

    fun fetchPreviewList() {
        _previewList.value = listOf(
            PreviewData(1, image = R.drawable.ic_check_green, date = "D+10"),
            PreviewData(2, image = R.drawable.ic_check_green, date = "D+10"),
            PreviewData(3, image = R.drawable.ic_check_green, date = "D+10"),
            PreviewData(4, image = R.drawable.ic_check_green, date = "D+10"),
        )
    }
}