package com.capstone.patech_android.ui.record

import android.net.Uri
import android.util.Log
import androidx.lifecycle.*
import com.capstone.patech_android.data.api.ServiceBuilder
import com.capstone.patech_android.data.request.RecordRequest
import com.capstone.patech_android.data.response.PreviewImage
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RecordViewModel : ViewModel() {

    val recordText = MutableLiveData("")

    val eventWater = MutableLiveData(false)
    val eventTrowel = MutableLiveData(false)

    private val _previewList = MutableLiveData<List<PreviewImage>>()
    val previewList: LiveData<List<PreviewImage>> = _previewList

    val image = MutableLiveData<Uri?>()
    lateinit var imageBase64: String

    private val _overlapImage = MutableLiveData<String?>()
    val overlapImage: LiveData<String?> = _overlapImage

    val validImage = MediatorLiveData<Boolean>().apply {
        addSource(image) {
            this.value = it != null
        }
    }

    fun setImage(imgUri: Uri) {
        image.value = imgUri
    }

    fun resetOverlapImage() {
        _overlapImage.value = null
    }

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

    fun postRecord(plantId: Int) {
        viewModelScope.launch {
            try {
                ServiceBuilder.cameraService.postRecord(
                    RecordRequest(
                        plant = plantId,
                        text = recordText.value.orEmpty(),
                        image = imageBase64,
                        eventTrowel = eventTrowel.value ?: false,
                        eventWater = eventWater.value ?: false
                    )
                )
            } catch (e: HttpException) {
                Log.d("postRecord", e.message())
            }
        }
    }
}