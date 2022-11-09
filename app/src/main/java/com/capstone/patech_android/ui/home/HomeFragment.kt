package com.capstone.patech_android.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.capstone.patech_android.R
import com.capstone.patech_android.base.BindingFragment
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentHomeBinding

class HomeFragment : ViewModelFragment<FragmentHomeBinding, HomeViewModel>(
    R.layout.fragment_home
) {
    override val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}