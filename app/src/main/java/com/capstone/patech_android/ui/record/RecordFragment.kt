package com.capstone.patech_android.ui.record

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentRecordBinding
import com.capstone.patech_android.util.navigate
import com.capstone.patech_android.util.popBackStack

class RecordFragment : ViewModelFragment<FragmentRecordBinding, RecordViewModel>(
    R.layout.fragment_record
) {
    override val viewModel: RecordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListener()
    }

    private fun addListener() {
        binding.layoutPhoto.setOnClickListener {
            navigate(R.id.action_recordFragment_to_photoModeDialog)
        }
        binding.btnBack.setOnClickListener {
            popBackStack()
        }
        binding.tvComplete.setOnClickListener {
            // 서버 연동
            popBackStack()
        }
    }
}