package com.capstone.patech_android.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentPaListBinding

class PaListFragment : ViewModelFragment<FragmentPaListBinding, PaListViewModel>(
    R.layout.fragment_pa_list
) {
    override val viewModel: PaListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}