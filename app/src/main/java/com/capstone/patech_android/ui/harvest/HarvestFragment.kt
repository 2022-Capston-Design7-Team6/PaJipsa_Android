package com.capstone.patech_android.ui.harvest

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentHarvestBinding
import com.capstone.patech_android.ui.dialog.PhotoModeDialog.Companion.HARVEST_AFTER_CAMERA
import com.capstone.patech_android.ui.dialog.PhotoModeDialog.Companion.HARVEST_BEFORE_CAMERA
import com.capstone.patech_android.ui.harvest.aftercamera.AfterCameraFragment.Companion.AFTER_URI
import com.capstone.patech_android.ui.harvest.beforecamera.BeforeCameraFragment.Companion.BEFORE_URI
import com.capstone.patech_android.util.ImageResolver
import com.capstone.patech_android.util.databinding.imageCoil
import com.capstone.patech_android.util.navigateWithData
import com.capstone.patech_android.util.popBackStack

class HarvestFragment : ViewModelFragment<FragmentHarvestBinding, HarvestViewModel>(
    R.layout.fragment_harvest
) {
    override val viewModel: HarvestViewModel by viewModels()
    private val args: HarvestFragmentArgs by navArgs()

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
            binding.progressBar.visibility = View.VISIBLE
            viewModel.postHarvest(args.plantId)
            popBackStack()
            Toast.makeText(context, "파 수확이 완료되었어요!", Toast.LENGTH_SHORT).show()
            binding.progressBar.visibility = View.GONE
        }
        binding.layoutPhotoBefore.setOnClickListener {
            navigateWithData(
                HarvestFragmentDirections.actionHarvestFragmentToPhotoModeDialog(
                    fromView = HARVEST_BEFORE_CAMERA,
                    plantId = args.plantId
                )
            )
        }
        binding.layoutPhotoAfter.setOnClickListener {
            navigateWithData(
                HarvestFragmentDirections.actionHarvestFragmentToPhotoModeDialog(
                    fromView = HARVEST_AFTER_CAMERA,
                    plantId = args.plantId,
                    beforeImage = viewModel.beforeImage.value.toString()
                )
            )
        }
    }

    private fun getBeforeUri() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Uri>(
            BEFORE_URI
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