package com.capstone.patech_android.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.capstone.patech_android.databinding.DialogPhotoModeBinding
import com.capstone.patech_android.util.navigateWithData
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
                DEFAULT_CAMERA -> navigateWithData(PhotoModeDialogDirections.actionPhotoModeDialogToRecordCameraFragment())
                RECORD_CAMERA -> navigateWithData(PhotoModeDialogDirections.actionPhotoModeDialogToRecordCameraFragment())
                HARVEST_BEFORE_CAMERA -> navigateWithData(
                    PhotoModeDialogDirections.actionPhotoModeDialogToBeforeCameraFragment(
                        args.plantId
                    )
                )
                HARVEST_AFTER_CAMERA -> navigateWithData(
                    PhotoModeDialogDirections.actionPhotoModeDialogToAfterCameraFragment(
                        args.plantId,
                        args.beforeImage.orEmpty()
                    )
                )
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
        const val HARVEST_BEFORE_CAMERA = 2
        const val HARVEST_AFTER_CAMERA = 3
    }
}