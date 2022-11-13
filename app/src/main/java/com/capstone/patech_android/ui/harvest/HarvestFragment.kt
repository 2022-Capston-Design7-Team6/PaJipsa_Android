package com.capstone.patech_android.ui.harvest

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentHarvestBinding
import com.capstone.patech_android.util.navigate
import com.capstone.patech_android.util.popBackStack

class HarvestFragment : ViewModelFragment<FragmentHarvestBinding, HarvestViewModel>(
    R.layout.fragment_harvest
) {
    override val viewModel: HarvestViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListener()
    }

    private fun addListener() {
        binding.btnBack.setOnClickListener {
            popBackStack()
        }
        binding.tvComplete.setOnClickListener {
            // 서버 연동
            popBackStack()
        }
        binding.layoutPhotoBefore.setOnClickListener {
            navigate(R.id.action_recordFragment_to_photoModeDialog)
        }
        binding.layoutPhotoAfter.setOnClickListener {
            navigate(R.id.action_recordFragment_to_photoModeDialog)
        }
    }
}