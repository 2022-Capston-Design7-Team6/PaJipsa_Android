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
import com.capstone.patech_android.data.request.RegisterRequest
import com.capstone.patech_android.util.PairMediatorLiveData
import com.capstone.patech_android.util.TripleMediatorLiveData
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
                Log.d("postLogin", e.message())
            }
        }
    }


    // register
    val registerId = MutableLiveData("")
    val registerPw = MutableLiveData("")
    val pwCheck = MutableLiveData("")
    val nickname = MutableLiveData("")

    val isPasswordSame = MediatorLiveData<Boolean?>().apply {
        addSource(
            PairMediatorLiveData(registerPw, pwCheck)
        ) { triple ->
            val pw1 = triple.first!!
            val pw2 = triple.second!!

            if (pw1.isBlank() || pw2.isBlank()) {
                this.value = null
            } else {
                this.value = pw1.isNotBlank() && pw2.isNotBlank() && pw1 == pw2
            }
        }
    }

    val isRegisterClickable = MediatorLiveData<Boolean>().apply {
        addSource(
            TripleMediatorLiveData(registerId, isPasswordSame, nickname)
        ) { triple ->
            val id = triple.first
            val pw = triple.second
            val nickname = triple.third

            if (id != null && pw != null && nickname != null) {
                this.value = id.isNotBlank() && pw && nickname.isNotBlank()
            }
        }
    }

    val isRegisterSuccess = MutableLiveData(false)
    val showRegisterError = MutableLiveData<Boolean?>(null)
    val isRegisterFail = MutableLiveData<Boolean?>(null)

    fun postRegister() {
        viewModelScope.launch {
            try {
                val response = ServiceBuilder.authService.postRegister(
                    RegisterRequest(
                        username = registerId.value.orEmpty(),
                        password = registerPw.value.orEmpty(),
                        nickname = nickname.value.orEmpty()
                    )
                )
                when (response.status) {
                    200, 205 -> {
                        isRegisterSuccess.value = true
                        isRegisterFail.value = false
                        showRegisterError.value = false
                        val token = response.token
                        val nickname = response.nickname
                        if (token != null && nickname != null) {
                            setToken(token)
                            setNickName(nickname)
                        }
                    }
                    403 -> {
                        showRegisterError.value = true
                        isRegisterFail.value = false
                    }
                    else -> {
                        showRegisterError.value = false
                        isRegisterFail.value = true
                    }
                }
            } catch (e: HttpException) {
                isRegisterFail.value = true
                Log.d("postRegister", e.message())
            }
        }
    }
}