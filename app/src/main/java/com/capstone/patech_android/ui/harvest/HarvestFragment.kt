package com.capstone.patech_android.ui.harvest

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentHarvestBinding
import com.capstone.patech_android.ui.dialog.PhotoModeDialog.Companion.HARVEST_AFTER_CAMERA
import com.capstone.patech_android.ui.dialog.PhotoModeDialog.Companion.RECORD_CAMERA
import com.capstone.patech_android.ui.harvest.aftercamera.AfterCameraFragment.Companion.AFTER_URI
import com.capstone.patech_android.ui.record.RecordCameraFragment
import com.capstone.patech_android.util.ImageResolver
import com.capstone.patech_android.util.databinding.imageCoil
import com.capstone.patech_android.util.navigateWithData
import com.capstone.patech_android.util.popBackStack

class HarvestFragment : ViewModelFragment<FragmentHarvestBinding, HarvestViewModel>(
    R.layout.fragment_harvest
) {
    override val viewModel: HarvestViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListener()
        getBeforeUri()
        getAfterUri()
    }

    private fun addListener() {
        binding.btnBack.setOnClickListener {
            popBackStack()
        }
        binding.tvComplete.setOnClickListener {
            viewModel.postHarvest()
            popBackStack()
        }
        binding.layoutPhotoBefore.setOnClickListener {
            navigateWithData(HarvestFragmentDirections.actionHarvestFragmentToPhotoModeDialog(RECORD_CAMERA))
        }
        binding.layoutPhotoAfter.setOnClickListener {
            navigateWithData(HarvestFragmentDirections.actionHarvestFragmentToPhotoModeDialog(HARVEST_AFTER_CAMERA))
        }
    }

    private fun getBeforeUri() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Uri>(
            RecordCameraFragment.SAVED_URI
        )?.observe(viewLifecycleOwner) {
            binding.ivPhotoBefore.imageCoil(it)
            viewModel.setBeforeImage(requireNotNull(it))
            viewModel.beforeImageBase64 =
                ImageResolver(requireContext()).createImgBase64(
                    requireNotNull(viewModel.beforeImage.value)
                )
        }
    }

    private fun getAfterUri() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Uri>(
            AFTER_URI
        )?.observe(viewLifecycleOwner) {
            binding.ivPhotoAfter.imageCoil(it)
            viewModel.setAfterImage(requireNotNull(it))
            viewModel.afterImageBase64 =
                ImageResolver(requireContext()).createImgBase64(
                    requireNotNull(viewModel.afterImage.value)
                )
        }
    }

}