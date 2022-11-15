package com.capstone.patech_android.ui.harvest

import android.net.Uri
import android.util.Log
import androidx.lifecycle.*
import com.capstone.patech_android.R
import com.capstone.patech_android.data.api.ServiceBuilder
import com.capstone.patech_android.data.model.PreviewData
import com.capstone.patech_android.data.request.HarvestRequest
import com.capstone.patech_android.util.PairMediatorLiveData
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HarvestViewModel : ViewModel() {

    // TODO : 식물 이미지 넘겨받기 viewModel args
    private val _plantId = MutableLiveData<Int>()
    val plantId: LiveData<Int> = _plantId

    private val _previewList = MutableLiveData<List<PreviewData>>()
    val previewList: LiveData<List<PreviewData>> = _previewList

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

    fun fetchPreviewList() {
        _previewList.value = listOf(
            PreviewData(1, image = R.drawable.ic_check_green, date = "D+10"),
            PreviewData(2, image = R.drawable.ic_check_green, date = "D+10"),
            PreviewData(3, image = R.drawable.ic_check_green, date = "D+10"),
            PreviewData(4, image = R.drawable.ic_check_green, date = "D+10"),
        )
    }

    fun setBeforeImage(imgUri: Uri) {
        beforeImage.value = imgUri
    }

    fun setAfterImage(imgUri: Uri) {
        afterImage.value = imgUri
    }

    fun postHarvest() {
        viewModelScope.launch {
            try {
                val plantId = plantId.value
                if (plantId != null) {
                    ServiceBuilder.cameraService.postHarvest(
                        HarvestRequest(
                            plantId,
                            beforeImageBase64,
                            afterImageBase64
                        )
                    )
                }
                Log.d("postHarvest", "서버")
            } catch (e: HttpException) {
                Log.d("postHarvest", e.message())
            }
        }
    }
}