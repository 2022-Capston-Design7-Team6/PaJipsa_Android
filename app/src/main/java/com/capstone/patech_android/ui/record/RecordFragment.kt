package com.capstone.patech_android.ui.record

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentRecordBinding
import com.capstone.patech_android.ui.dialog.PhotoModeDialog.Companion.RECORD_CAMERA
import com.capstone.patech_android.ui.record.RecordCameraFragment.Companion.SAVED_URI
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
        getSavedUri()
    }

    private fun addListener() {
        binding.layoutPhoto.setOnClickListener {
            navigateWithData(
                RecordFragmentDirections.actionRecordFragmentToPhotoModeDialog(RECORD_CAMERA)
            )
        }
        binding.btnBack.setOnClickListener {
            popBackStack()
        }
        binding.tvComplete.setOnClickListener {
            // 서버 연동
            popBackStack()
        }
    }

    private fun getSavedUri() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Uri>(SAVED_URI)
            ?.observe(viewLifecycleOwner) {
                binding.ivPhoto.imageCoil(it)
            }
    }
}