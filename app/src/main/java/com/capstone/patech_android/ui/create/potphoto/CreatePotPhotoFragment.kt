package com.capstone.patech_android.ui.create.potphoto

import android.os.Bundle
import android.view.View
import androidx.navigation.navGraphViewModels
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentCreatePotPhotoBinding
import com.capstone.patech_android.ui.create.CreateViewModel
import com.capstone.patech_android.util.navigate
import com.capstone.patech_android.util.popBackStack

class CreatePotPhotoFragment : ViewModelFragment<FragmentCreatePotPhotoBinding, CreateViewModel>(
    R.layout.fragment_create_pot_photo
) {
    override val viewModel: CreateViewModel by navGraphViewModels(R.id.create_nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListener()
        setBtnNextEnable()
    }

    private fun addListener() {
        binding.btnBack.setOnClickListener {
            popBackStack()
        }
        binding.btnPhotoAdd.setOnClickListener {
            PotPhotoInformationDialog { isConfirm ->
                // isConfirm value 로 dialog 최초 1회만 보여지도록
                // camera open
            }.show(childFragmentManager, "dialog_info")
        }
        binding.tvNext.setOnClickListener {
            navigate(R.id.action_createPotPhotoFragment_to_createPotInfoFragment)
        }
    }

    private fun setBtnNextEnable() {
//        viewModel.image.observe(viewLifecycleOwner) { image ->
//            if (image != null) {
//                binding.tvNext.bindTextAbleOrUnable(true)
//            } else {
//                binding.tvNext.bindTextAbleOrUnable(false)
//            }
//        }
    }
}