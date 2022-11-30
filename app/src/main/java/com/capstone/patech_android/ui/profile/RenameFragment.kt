package com.capstone.patech_android.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
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
        viewModel.modifyNickname.value = args.nickname
        addListener()
        setObserver()
    }

    private fun addListener() {
        binding.btnBack.setOnClickListener {
            popBackStack()
        }
        binding.btnNameCheck.setOnClickListener {
            viewModel.checkNickNameValid()
        }
    }

    private fun setObserver() {
        viewModel.isCheckClickable.observe(viewLifecycleOwner) { isClickable ->
            when (isClickable) {
                true -> binding.btnNameCheck.setImageResource(R.drawable.ic_check_green)
                false -> binding.btnNameCheck.setImageResource(R.drawable.ic_check_gray)
            }
        }

        viewModel.isNameCheckValid.observe(viewLifecycleOwner) { isSuccess ->
            if(isSuccess == true) {
                popBackStack()
                Toast.makeText(context, "닉네임이 변경되었습니다.", Toast.LENGTH_LONG).show()
            }
        }
    }
}