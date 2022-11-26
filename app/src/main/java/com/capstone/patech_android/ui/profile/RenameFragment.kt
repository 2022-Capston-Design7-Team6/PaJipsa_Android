package com.capstone.patech_android.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentProfileRenameBinding
import com.capstone.patech_android.util.popBackStack

class RenameFragment : ViewModelFragment<FragmentProfileRenameBinding, ProfileViewModel>(
    R.layout.fragment_profile_rename
) {
    override val viewModel: ProfileViewModel by viewModels()
    private val args: ProfileFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.originNickname.value = args.nickname
        addListener()
    }

    private fun addListener() {
        binding.btnBack.setOnClickListener {
            popBackStack()
        }
    }
}