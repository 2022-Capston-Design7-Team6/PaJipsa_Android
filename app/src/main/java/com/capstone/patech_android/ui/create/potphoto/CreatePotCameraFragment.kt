package com.capstone.patech_android.ui.create.potphoto

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentCreatePotCameraBinding
import com.capstone.patech_android.ui.create.CreateViewModel
import com.capstone.patech_android.util.PermissionUtil
import com.capstone.patech_android.util.popBackStack
import java.text.SimpleDateFormat
import java.util.*

class CreatePotCameraFragment : ViewModelFragment<FragmentCreatePotCameraBinding, CreateViewModel>(
    R.layout.fragment_create_pot_camera
) {
    override val viewModel: CreateViewModel by navGraphViewModels(R.id.create_nav_graph)

    private var imageCapture: ImageCapture? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        permissionCheck()
    }

    private fun permissionCheck() {
        val permissionList =
            listOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)

        if (!PermissionUtil.checkPermission(requireContext(), permissionList)) {
            PermissionUtil.requestPermission(requireActivity(), permissionList)
        } else {
            Log.d("permissionCheck", "true")
            openCamera()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d("permissionResult", "true")
            openCamera()
        } else {
            Log.d("permissionResult", "false")
        }
    }

    private fun openCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.camera.surfaceProvider)
                }
            imageCapture = ImageCapture.Builder().build()

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
                binding.btnCapture.setOnClickListener {
                    savePhoto()
                }
            } catch (e: Exception) {
                Log.d("openCamera", "$e")
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun savePhoto() {
        // Get a stable reference of the modifiable image capture use case
        imageCapture = imageCapture ?: return

        val name = SimpleDateFormat("yy-mm-dd", Locale.US)
            .format(System.currentTimeMillis())
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
            }
        }

        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(
                requireContext().contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            .build()

        imageCapture?.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(e: ImageCaptureException) {
                    Log.e("savePhoto", "Photo capture failed: ${e.message}", e)
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    findNavController().previousBackStackEntry?.savedStateHandle?.set(
                        CREATE_POT,
                        output.savedUri
                    )
                    popBackStack()
                    Log.d("savedPhoto", "${output.savedUri}")
                }
            }
        )
    }

    companion object {
        const val CREATE_POT = "createPot"
    }
}