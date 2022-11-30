package com.capstone.patech_android.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.data.SharedPreferenceController
import com.capstone.patech_android.databinding.FragmentProfileRenameBinding
import com.capstone.patech_android.util.popBackStack

class RenameFragment : ViewModelFragment<FragmentProfileRenameBinding, ProfileViewModel>(
    R.layout.fragment_profile_rename
) {
    override val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sdfNickname = SharedPreferenceController.getNickName()
        viewModel.originNickname.value = sdfNickname
        viewModel.modifyNickname.value = sdfNickname
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
                SharedPreferenceController.setNickName(viewModel.modifyNickname.value.orEmpty())
            }
        }
    }
}