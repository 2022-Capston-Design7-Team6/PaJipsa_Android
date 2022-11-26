package com.capstone.patech_android.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {
    val originNickname = MutableLiveData("")
    val modifyNickname = MutableLiveData("")
}