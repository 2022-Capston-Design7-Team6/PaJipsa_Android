package com.capstone.patech_android.ui.harvest

import android.net.Uri
import android.util.Log
import androidx.lifecycle.*
import com.capstone.patech_android.data.api.ServiceBuilder
import com.capstone.patech_android.data.request.HarvestRequest
import com.capstone.patech_android.data.response.PreviewImage
import com.capstone.patech_android.util.PairMediatorLiveData
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HarvestViewModel : ViewModel() {
    private val _previewList = MutableLiveData<List<PreviewImage>>()
    val previewList: LiveData<List<PreviewImage>> = _previewList

    private val _overlapImage = MutableLiveData<String>()
    val overlapImage: LiveData<String> = _overlapImage

    val beforeImage = MutableLiveData<Uri?>()
    val afterImage = MutableLiveData<Uri?>()

    lateinit var beforeImageBase64: String
    lateinit var afterImageBase64: String

    val validPhotos = MediatorLiveData<Boolean>().apply {
        addSource(
            PairMediatorLiveData(beforeImage, afterImage)
        ) { triple ->
            val beforeImage = triple.first
            val afterImage = triple.second
            this.value = beforeImage != null && afterImage != null
        }
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

    fun setOverlapImage(image: String) {
        _overlapImage.value = image
    }

    fun setBeforeImage(imgUri: Uri) {
        beforeImage.value = imgUri
    }

    fun setAfterImage(imgUri: Uri) {
        afterImage.value = imgUri
    }

    fun postHarvest(plantId: Int) {
        viewModelScope.launch {
            try {
                ServiceBuilder.cameraService.postHarvest(
                    HarvestRequest(
                        plantId,
                        beforeImageBase64,
                        afterImageBase64
                    )
                )
                Log.d("postHarvest", "서버")
            } catch (e: HttpException) {
                Log.d("postHarvest", e.message())
            }
        }
    }
}