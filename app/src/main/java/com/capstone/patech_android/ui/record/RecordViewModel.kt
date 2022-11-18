package com.capstone.patech_android.ui.record

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.patech_android.data.api.ServiceBuilder
import com.capstone.patech_android.data.response.PreviewImage
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RecordViewModel : ViewModel() {

    private val _previewList = MutableLiveData<List<PreviewImage>>()
    val previewList: LiveData<List<PreviewImage>> = _previewList

    private val _overlapImage = MutableLiveData<String>()
    val overlapImage: LiveData<String> = _overlapImage

    fun setOverlapImage(image: String) {
        _overlapImage.value = image
    }

    fun fetchPreviewList(plantId: Int) {
        viewModelScope.launch {
            try {
                val response = ServiceBuilder.cameraService.getPreview(plantId)
                _previewList.postValue(response.photoList)
            } catch (e: HttpException) {
                Log.d("fetchPreviewList", e.message())
            }
        }
    }
}