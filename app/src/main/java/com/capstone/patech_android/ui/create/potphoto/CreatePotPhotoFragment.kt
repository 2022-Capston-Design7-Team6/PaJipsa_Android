package com.capstone.patech_android.ui.create.potphoto

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentCreatePotPhotoBinding
import com.capstone.patech_android.ui.create.CreateViewModel
import com.capstone.patech_android.ui.create.potphoto.CreatePotCameraFragment.Companion.CREATE_POT
import com.capstone.patech_android.util.ImageResolver
import com.capstone.patech_android.util.databinding.imageCoil
import com.capstone.patech_android.util.navigate
import com.capstone.patech_android.util.popBackStack

class CreatePotPhotoFragment : ViewModelFragment<FragmentCreatePotPhotoBinding, CreateViewModel>(
    R.layout.fragment_create_pot_photo
) {
    override val viewModel: CreateViewModel by navGraphViewModels(R.id.create_nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListener()
        getUri()
    }

    private fun addListener() {
        binding.btnBack.setOnClickListener {
            popBackStack()
        }
        binding.btnPhotoAdd.setOnClickListener {
            PotPhotoInformationDialog { isConfirm ->
                if (isConfirm) {
                    openCamera()
                }
            }.show(childFragmentManager, "dialog_info")
        }
        binding.tvNext.setOnClickListener {
            navigate(R.id.action_createPotPhotoFragment_to_createPotInfoFragment)
        }
    }

    private fun openCamera() {
        navigate(R.id.action_createPotPhotoFragment_to_createPotCameraFragment)
    }

    private fun getUri() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Uri>(
            CREATE_POT
        )?.observe(viewLifecycleOwner) {
            binding.ivPhoto.imageCoil(it)
            viewModel.setImage(requireNotNull(it))
            viewModel.imageBase64 =
                ImageResolver(requireContext()).createImgBase64(
                    requireNotNull(viewModel.image.value)
                )
        }
    }
}