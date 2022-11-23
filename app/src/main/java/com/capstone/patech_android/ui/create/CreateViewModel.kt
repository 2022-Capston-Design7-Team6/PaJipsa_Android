package com.capstone.patech_android.ui.create

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.patech_android.util.PairMediatorLiveData

class CreateViewModel : ViewModel() {
    // category
    private val _selectedCategory = MutableLiveData<Int>()
    val selectedCategory: LiveData<Int> = _selectedCategory

    fun setCategory(category: Int) {
        _selectedCategory.value = category
    }


    // name
    val plantName = MutableLiveData("")

    val isNameNotBlank = MediatorLiveData<Boolean>().apply {
        addSource(plantName) {
            this.value = it.isNotBlank()
        }
    }

    val isNameNotMultiple = MediatorLiveData<Boolean>().apply {
        addSource(plantName) {
            // 서버 연동 후 중복된 이름 아닌 경우에 true
            this.value = true
        }
    }

    val isNameValid = MediatorLiveData<Boolean>().apply {
        addSource(
            PairMediatorLiveData(isNameNotBlank, isNameNotMultiple)
        ) { triple ->
            val isNameNotBlank = triple.first
            val isNameNotMultiple = triple.second
            if (isNameNotBlank != null && isNameNotMultiple != null) {
                this.value = isNameNotBlank && isNameNotMultiple
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
}