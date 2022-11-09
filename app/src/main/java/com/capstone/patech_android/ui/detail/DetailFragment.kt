package com.capstone.patech_android.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentDetailBinding

class DetailFragment : ViewModelFragment<FragmentDetailBinding, DetailViewModel>(
    R.layout.fragment_detail
) {
    override val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}