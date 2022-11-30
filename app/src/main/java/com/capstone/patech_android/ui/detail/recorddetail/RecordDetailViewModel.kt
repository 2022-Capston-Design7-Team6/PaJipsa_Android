package com.capstone.patech_android.ui.detail.recorddetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.patech_android.data.api.ServiceBuilder
import com.capstone.patech_android.util.timeFormatToCalender
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.*

class RecordDetailViewModel : ViewModel() {

    val beforeImage = MutableLiveData<String?>(null)
    val image = MutableLiveData("")

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    val isHarvest = MutableLiveData(false)
    val isWater = MutableLiveData(false)
    val isTrowel = MutableLiveData(false)

    private val _record = MutableLiveData<String>()
    val record: LiveData<String> = _record

    fun fetchDetailData(id: Int) {
        viewModelScope.launch {
            try {
                val response = ServiceBuilder.plantService.getOnePlantDetail(
                    id
                )
                beforeImage.value = response.beforeImage
                image.value = response.image
                _date.value = dateFormatter(response.date)
                isHarvest.value = response.eventHarvest
                isWater.value = response.eventWater
                isTrowel.value = response.eventTrowel
                _record.value = response.record
            } catch (e: HttpException) {
                Log.d("fetchDetailData", e.message().toString())
            }
        }
    }

    private fun dateFormatter(inputDate: String): String {
        val time = timeFormatToCalender(inputDate)!!
        val year = time.get(Calendar.YEAR)
        val month = time.get(Calendar.MONTH) + 1
        val date = time.get(Calendar.DATE)
        return "$year.$month.$date"
    }
}