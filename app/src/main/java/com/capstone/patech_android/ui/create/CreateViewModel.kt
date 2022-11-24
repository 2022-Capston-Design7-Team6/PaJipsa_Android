package com.capstone.patech_android.ui.create

import android.net.Uri
import android.util.Log
import androidx.lifecycle.*
import com.capstone.patech_android.data.api.ServiceBuilder
import com.capstone.patech_android.data.request.PlantCreateRequest
import com.capstone.patech_android.util.PairMediatorLiveData
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CreateViewModel : ViewModel() {
    // category
    private val _selectedCategory = MutableLiveData<Int>()
    val selectedCategory: LiveData<Int> = _selectedCategory

    fun setCategory(category: Int) {
        _selectedCategory.value = category
    }


    // name
    val plantName = MutableLiveData("")

    val checkedPlantName = MutableLiveData<String?>(null)

    val isNameNotBlank = MediatorLiveData<Boolean>().apply {
        addSource(plantName) {
            this.value = it.isNotBlank()
        }
    }

    val isNameCheckValid = MutableLiveData<Boolean?>(null)

    val isNameModified = MediatorLiveData<Boolean>().apply {
        addSource(
            PairMediatorLiveData(plantName, checkedPlantName)
        ) { triple ->
            val plantName = triple.first
            val checkedPlantName = triple.second

            if (plantName != null && checkedPlantName != null) {
                this.value = plantName != checkedPlantName
            }
        }
    }

    val isNameValid = MediatorLiveData<Boolean>().apply {
        addSource(
            PairMediatorLiveData(isNameNotBlank, isNameCheckValid)
        ) { triple ->
            val isNameNotBlank = triple.first
            val isNameNotMultiple = triple.second
            if (isNameNotBlank != null && isNameNotMultiple != null) {
                this.value = isNameNotBlank && isNameNotMultiple
            }
        }
    }

    fun checkPlantNameValid() {
        viewModelScope.launch {
            try {
                val response = ServiceBuilder.plantService.checkPlantName(plantName.value.orEmpty())
                checkedPlantName.value = plantName.value
                when (response.code()) {
                    200 -> isNameCheckValid.value = true
                    else -> isNameCheckValid.value = false
                }
            } catch (e: HttpException) {
                Log.d("checkPlantNameValid", e.message())
            }
        }
    }


    // pot photo
    val image = MutableLiveData<Uri?>()
    lateinit var imageBase64: String

    fun setImage(imgUri: Uri) {
        image.value = imgUri
    }

    val validImage = MediatorLiveData<Boolean>().apply {
        addSource(image) {
            this.value = it != null
        }
    }


    // pot info
    val height = MutableLiveData("")

    val ratio = MutableLiveData(0)

    val canComplete = MediatorLiveData<Boolean>().apply {
        addSource(
            PairMediatorLiveData(height, ratio)
        ) { triple ->
            val height = triple.first!!
            val ratio = triple.second!!
            this.value = height.isNotBlank() && ratio != 0
        }
    }

    private val _successCreate = MutableLiveData<Boolean>()
    val successCreate: LiveData<Boolean> = _successCreate

    fun postPlantCreate() {
        viewModelScope.launch {
            try {
                _successCreate.value = false
                val response = ServiceBuilder.plantService.postPlantCreate(
                    PlantCreateRequest(
                        plantName = plantName.value.orEmpty(),
                        category = selectedCategory.value!!,
                        potRatio = ratio.value!!.toFloat() / 100,
                        potSize = height.value!!.toInt()
                    )
                )

                if (response.code() == 201) {
                    _successCreate.value = true
                }
            } catch (e: HttpException) {
                Log.d("postPlantCreate", e.message())
            }
        }
    }
}