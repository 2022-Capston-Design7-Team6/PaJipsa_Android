package com.capstone.patech_android.ui.detail.recorddetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentRecordDetailBinding
import com.capstone.patech_android.util.popBackStack

class RecordDetailFragment : ViewModelFragment<FragmentRecordDetailBinding, RecordDetailViewModel>(
    R.layout.fragment_record_detail
) {
    override val viewModel: RecordDetailViewModel by viewModels()
    private val args: RecordDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListener()
    }

    private fun addListener() {
        binding.btnBack.setOnClickListener {
            popBackStack()
        }
    }
}