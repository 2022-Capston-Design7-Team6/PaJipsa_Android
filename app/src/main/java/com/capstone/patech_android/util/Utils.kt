package com.capstone.patech_android.util

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService

object Utils : BaseUtil() {
    private lateinit var inputMethodManager: InputMethodManager
    private lateinit var applicationContext: Context

    override fun initialize(context: Context) {
        super.initialize(context)
        applicationContext = context
        inputMethodManager = checkNotNull(context.getSystemService()) {
            "Application is instantiated before any other class when the process for your application/package is created"
        }
    }
}