package com.capstone.patech_android.ui.dialog

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.capstone.patech_android.databinding.DialogPhotoModeBinding
import com.capstone.patech_android.ui.harvest.aftercamera.AfterCameraFragment
import com.capstone.patech_android.ui.harvest.beforecamera.BeforeCameraFragment
import com.capstone.patech_android.ui.record.RecordCameraFragment
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
                RECORD_CAMERA -> {
                    navigateWithData(
                        PhotoModeDialogDirections.actionPhotoModeDialogToRecordCameraFragment(
                            args.plantId
                        )
                    )
                }
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

    @SuppressLint("IntentReset")
    private fun openGallery() {
        val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                if (isGranted) {
                    val intent = Intent(Intent.ACTION_PICK)
                    intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    intent.type = "image/*"
                    getImage.launch(intent)
                }
            }
        binding.btnGallery.setOnClickListener {
            requestPermissionLauncher.launch(
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    }

    private val getImage = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.data != null) {
            val imageUri = result.data?.data
            findNavController().previousBackStackEntry?.savedStateHandle?.set(
                setTag(),
                imageUri
            )
            dismiss()
        }
    }

    private fun setTag(): String {
        return when (args.fromView) {
            RECORD_CAMERA -> RecordCameraFragment.RECORD_URI
            HARVEST_BEFORE_CAMERA -> BeforeCameraFragment.BEFORE_URI
            else -> AfterCameraFragment.AFTER_URI
        }
    }

    companion object {
        const val DEFAULT_CAMERA = 0
        const val RECORD_CAMERA = 1
        const val HARVEST_BEFORE_CAMERA = 2
        const val HARVEST_AFTER_CAMERA = 3
    }
}