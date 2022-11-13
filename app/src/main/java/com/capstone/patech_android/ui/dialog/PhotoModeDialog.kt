package com.capstone.patech_android.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstone.patech_android.databinding.DialogPhotoModeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PhotoModeDialog : BottomSheetDialogFragment() {
    lateinit var binding: DialogPhotoModeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogPhotoModeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}