package com.capstone.patech_android.ui.record

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentRecordCameraBinding
import com.capstone.patech_android.util.popBackStack

class RecordCameraFragment : ViewModelFragment<FragmentRecordCameraBinding, RecordViewModel>(
    R.layout.fragment_record_camera
) {
    override val viewModel: RecordViewModel by viewModels()

    private lateinit var previewAdapter: RecordPreviewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previewAdapter = RecordPreviewAdapter()
        viewModel.fetchPreviewList()
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
}