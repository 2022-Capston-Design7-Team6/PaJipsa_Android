package com.capstone.patech_android.ui.patech

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentPatechBinding
import com.capstone.patech_android.util.popBackStack

class PatechFragment : ViewModelFragment<FragmentPatechBinding, PatechViewModel>(
    R.layout.fragment_patech
) {
    override val viewModel: PatechViewModel by viewModels()

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