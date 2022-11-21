package com.capstone.patech_android.ui.record

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentRecordBinding
import com.capstone.patech_android.ui.dialog.PhotoModeDialog.Companion.RECORD_CAMERA
import com.capstone.patech_android.ui.record.RecordCameraFragment.Companion.RECORD_URI
import com.capstone.patech_android.util.ImageResolver
import com.capstone.patech_android.util.databinding.imageCoil
import com.capstone.patech_android.util.navigateWithData
import com.capstone.patech_android.util.popBackStack

class RecordFragment : ViewModelFragment<FragmentRecordBinding, RecordViewModel>(
    R.layout.fragment_record
) {
    override val viewModel: RecordViewModel by viewModels()
    private val args: RecordFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListener()
        getRecordUri()
    }

    private fun addListener() {
        binding.layoutPhoto.setOnClickListener {
            navigateWithData(
                RecordFragmentDirections.actionRecordFragmentToPhotoModeDialog(
                    fromView = RECORD_CAMERA,
                    plantId = args.plantId
                )
            )
        }
        binding.btnBack.setOnClickListener {
            popBackStack()
        }
        binding.tvComplete.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.postRecord(args.plantId)
            popBackStack()
            Toast.makeText(context, "새로운 일지가 등록되었어요!", Toast.LENGTH_SHORT).show()
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun getRecordUri() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Uri>(RECORD_URI)
            ?.observe(viewLifecycleOwner) {
                binding.ivPhoto.imageCoil(it)
                viewModel.setImage(requireNotNull(it))
                viewModel.imageBase64 =
                    ImageResolver(requireContext()).createImgBase64(
                        requireNotNull(viewModel.image.value)
                    )
            }
    }
}