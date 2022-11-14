package com.capstone.patech_android

import android.app.Application
import com.capstone.patech_android.data.SharedPreferenceController
import com.capstone.patech_android.util.Utils

class PatechApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Utils.initialize(this)
        SharedPreferenceController.init(this)
    }
}