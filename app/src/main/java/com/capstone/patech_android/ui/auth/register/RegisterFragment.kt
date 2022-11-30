package com.capstone.patech_android.ui.auth.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.navGraphViewModels
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentAuthRegisterBinding
import com.capstone.patech_android.ui.MainActivity
import com.capstone.patech_android.ui.auth.AuthViewModel
import com.capstone.patech_android.util.popBackStack

class RegisterFragment : ViewModelFragment<FragmentAuthRegisterBinding, AuthViewModel>(
    R.layout.fragment_auth_register
) {
    override val viewModel: AuthViewModel by navGraphViewModels(R.id.auth_nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListener()
        addObserver()
    }

    private fun addListener() {
        binding.btnBack.setOnClickListener {
            popBackStack()
        }
        binding.tvNext.setOnClickListener {
            viewModel.postRegister()
        }
    }

    private fun addObserver() {
        viewModel.isRegisterSuccess.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finish()
            }
        }
    }
}