package com.capstone.patech_android.ui.profile

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.patech_android.data.api.ServiceBuilder
import com.capstone.patech_android.data.request.NickNamePut
import com.capstone.patech_android.util.PairMediatorLiveData
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ProfileViewModel : ViewModel() {
    val originNickname = MutableLiveData("")
    val modifyNickname = MutableLiveData("")

    val isCheckClickable = MediatorLiveData<Boolean>().apply {
        addSource(
            PairMediatorLiveData(originNickname, modifyNickname)
        ) { triple ->
            val origin = triple.first
            val modify = triple.second

            if (origin != null && modify != null) {
                this.value = origin != modify && modify.isNotBlank()
            }
        }
    }

    val isNameCheckValid = MutableLiveData<Boolean?>(null)

    fun checkNickNameValid() {
        viewModelScope.launch {
            try {
                val response = ServiceBuilder.authService.putNickName(
                    NickNamePut(modifyNickname.value.orEmpty())
                )
                when (response.code()) {
                    200 -> isNameCheckValid.value = true
                    else -> isNameCheckValid.value = false
                }
            } catch (e: HttpException) {
                Log.d("checkNickNameValid", e.message())
            }
        }
    }
}