package com.capstone.patech_android.ui.create.category

import android.os.Bundle
import android.view.View
import androidx.navigation.navGraphViewModels
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentCreateCategoryBinding
import com.capstone.patech_android.ui.create.CreateViewModel
import com.capstone.patech_android.util.navigate
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
        binding.layoutGreenOnion.navigateToNextStep(0)
        binding.layoutChive.navigateToNextStep(1)
        binding.layoutOnion.navigateToNextStep(2)
    }

    private fun View.navigateToNextStep(category: Int) {
        this.setOnClickListener {
            viewModel.setCategory(category)
            navigate(R.id.action_createCategoryFragment_to_createNameFragment)
        }
    }
}