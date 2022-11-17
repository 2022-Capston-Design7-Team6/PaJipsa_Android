package com.capstone.patech_android.ui.harvest.aftercamera

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentHarvestAfterCameraBinding
import com.capstone.patech_android.ui.harvest.HarvestViewModel
import com.capstone.patech_android.util.PermissionUtil
import com.capstone.patech_android.util.popBackStack
import java.text.SimpleDateFormat
import java.util.*

class AfterCameraFragment : ViewModelFragment<FragmentHarvestAfterCameraBinding, HarvestViewModel>(
    R.layout.fragment_harvest_after_camera
) {
    override val viewModel: HarvestViewModel by viewModels()
    private val args: AfterCameraFragmentArgs by navArgs()
    private lateinit var previewAdapter: AfterCameraPreviewAdapter

    private var imageCapture: ImageCapture? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        permissionCheck()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previewAdapter = AfterCameraPreviewAdapter(viewModel)
        viewModel.fetchPreviewList(args.plantId)
        initRVAdapter()
        setPreviewList()
        addListener()
    }

    private fun initRVAdapter() {
        binding.rvPreview.adapter = previewAdapter
    }

    private fun setPreviewList() {
        viewModel.previewList.observe(viewLifecycleOwner) { list ->
            list?.let {
                with(previewAdapter) { submitList(list) }
            }
        }
    }

    private fun addListener() {
        binding.btnBack.setOnClickListener {
            popBackStack()
        }
    }

    // camera
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
        Log.d("openCamera", "openCamera")
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

        // Create time stamped name and MediaStore entry.
        val name = SimpleDateFormat("yy-mm-dd", Locale.US)
            .format(System.currentTimeMillis())
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
            }
        }

        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(
                requireContext().contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            .build()

        // Set up image capture listener, which is triggered after photo has been taken
        imageCapture?.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(e: ImageCaptureException) {
                    Log.e("savePhoto", "Photo capture failed: ${e.message}", e)
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    findNavController().previousBackStackEntry?.savedStateHandle?.set(
                        AFTER_URI,
                        output.savedUri
                    )
                    popBackStack()
                    val msg = "Photo capture succeeded: ${output.savedUri}"
                    Log.d("savedPhoto", msg)
                }
            }
        )
    }

    companion object {
        const val AFTER_URI = "afterUri"
    }
}