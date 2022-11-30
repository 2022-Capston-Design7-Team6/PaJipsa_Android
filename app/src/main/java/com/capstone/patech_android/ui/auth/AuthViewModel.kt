package com.capstone.patech_android.ui.auth

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.patech_android.data.SharedPreferenceController.setNickName
import com.capstone.patech_android.data.SharedPreferenceController.setToken
import com.capstone.patech_android.data.api.ServiceBuilder
import com.capstone.patech_android.data.request.LoginRequest
import com.capstone.patech_android.util.PairMediatorLiveData
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AuthViewModel : ViewModel() {

    //Login
    val loginId = MutableLiveData("")
    val loginPw = MutableLiveData("")

    val isLoginClickable = MediatorLiveData<Boolean>().apply {
        addSource(
            PairMediatorLiveData(loginId, loginPw)
        ) { triple ->
            val id = triple.first
            val pw = triple.second

            if (id != null && pw != null) {
                this.value = id.isNotBlank() && pw.isNotBlank()
            }
        }
    }

    val isLoginSuccess = MutableLiveData(false)
    val showLoginError = MutableLiveData<Boolean?>(null)
    val isLoginFail = MutableLiveData<Boolean?>(null)

    fun postLogin() {
        viewModelScope.launch {
            try {
                val response = ServiceBuilder.authService.postLogin(
                    LoginRequest(
                        username = loginId.value.orEmpty(),
                        password = loginPw.value.orEmpty(),
                    )
                )
                when (response.status) {
                    200 -> {
                        isLoginSuccess.value = true
                        isLoginFail.value = false
                        showLoginError.value = false
                        val token = response.token
                        val nickname = response.nickname
                        if (token != null && nickname != null) {
                            setToken(token)
                            setNickName(nickname)
                        }
                    }
                    403 -> {
                        showLoginError.value = true
                        isLoginFail.value = false
                    }
                    else -> {
                        showLoginError.value = false
                        isLoginFail.value = true
                    }
                }
            } catch (e: HttpException) {
                isLoginFail.value = true
                Log.d("checkNickNameValid", e.message())
            }
        }
    }
}