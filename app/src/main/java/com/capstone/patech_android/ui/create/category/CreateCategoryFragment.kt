package com.capstone.patech_android.ui.create.category

import android.os.Bundle
import android.view.View
import androidx.navigation.navGraphViewModels
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentCreateCategoryBinding
import com.capstone.patech_android.ui.create.CreateViewModel
import com.capstone.patech_android.util.popBackStack

class CreateCategoryFragment : ViewModelFragment<FragmentCreateCategoryBinding, CreateViewModel>(
    R.layout.fragment_create_category
) {
    override val viewModel: CreateViewModel by navGraphViewModels(R.id.create_nav_graph)

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