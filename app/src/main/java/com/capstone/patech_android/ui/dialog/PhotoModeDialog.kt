package com.capstone.patech_android.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.capstone.patech_android.R
import com.capstone.patech_android.databinding.DialogPhotoModeBinding
import com.capstone.patech_android.util.navigate
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PhotoModeDialog : BottomSheetDialogFragment() {
    lateinit var binding: DialogPhotoModeBinding

    private val args: PhotoModeDialogArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogPhotoModeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        openCamera()
        openGallery()
        return binding.root
    }

    private fun openCamera() {
        binding.btnCamera.setOnClickListener {
            when (args.fromView) {
                DEFAULT_CAMERA -> navigate(R.id.action_photoModeDialog_to_recordCameraFragment)
                RECORD_CAMERA -> navigate(R.id.action_photoModeDialog_to_recordCameraFragment)
                HARVEST_AFTER_CAMERA -> navigate(R.id.action_photoModeDialog_to_afterCameraFragment)
            }
        }
    }

    private fun openGallery() {
        binding.btnGallery.setOnClickListener {

        }
    }

    companion object {
        const val DEFAULT_CAMERA = 0
        const val RECORD_CAMERA = 1
        const val HARVEST_AFTER_CAMERA = 2
    }
}