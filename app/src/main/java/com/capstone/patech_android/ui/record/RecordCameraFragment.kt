package com.capstone.patech_android.ui.record

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentRecordCameraBinding

class RecordCameraFragment : ViewModelFragment<FragmentRecordCameraBinding, RecordViewModel>(
    R.layout.fragment_record_camera
) {
    override val viewModel: RecordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}