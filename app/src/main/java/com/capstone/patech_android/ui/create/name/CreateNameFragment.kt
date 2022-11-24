package com.capstone.patech_android.ui.create.name

import android.os.Bundle
import android.view.View
import androidx.navigation.navGraphViewModels
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentCreateNameBinding
import com.capstone.patech_android.ui.create.CreateViewModel
import com.capstone.patech_android.util.KeyBoardUtil
import com.capstone.patech_android.util.navigate
import com.capstone.patech_android.util.popBackStack

class CreateNameFragment : ViewModelFragment<FragmentCreateNameBinding, CreateViewModel>(
    R.layout.fragment_create_name
) {
    override val viewModel: CreateViewModel by navGraphViewModels(R.id.create_nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        addListener()
    }

    private fun initView() {
        viewModel.selectedCategory.observe(viewLifecycleOwner) {
            binding.tvTitle.text = when (it) {
                0 -> "대파의"
                1 -> "쪽파의"
                else -> "양파의"
            }
        }

        viewModel.isNameNotBlank.observe(viewLifecycleOwner) { isNotBlank ->
            when (isNotBlank) {
                true -> binding.btnNameCheck.setImageResource(R.drawable.ic_check_green)
                false -> binding.btnNameCheck.setImageResource(R.drawable.ic_check_gray)
            }
        }
    }

    private fun addListener() {
        binding.btnBack.setOnClickListener {
            popBackStack()
        }
        binding.btnNameCheck.setOnClickListener {
            viewModel.checkPlantNameValid()
        }
        binding.tvNext.setOnClickListener {
            navigate(R.id.action_createNameFragment_to_createPotPhotoFragment)
            KeyBoardUtil.hide(requireActivity())
        }
    }
}