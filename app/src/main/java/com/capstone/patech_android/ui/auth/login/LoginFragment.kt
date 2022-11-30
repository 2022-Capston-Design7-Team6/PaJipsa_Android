package com.capstone.patech_android.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.navGraphViewModels
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentAuthLoginBinding
import com.capstone.patech_android.ui.MainActivity
import com.capstone.patech_android.ui.auth.AuthViewModel
import com.capstone.patech_android.util.KeyBoardUtil
import com.capstone.patech_android.util.popBackStack

class LoginFragment : ViewModelFragment<FragmentAuthLoginBinding, AuthViewModel>(
    R.layout.fragment_auth_login
) {
    override val viewModel: AuthViewModel by navGraphViewModels(R.id.auth_nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListener()
        setObserver()
    }

    private fun addListener() {
        binding.btnBack.setOnClickListener {
            popBackStack()
        }
        binding.tvLogin.setOnClickListener {
            viewModel.postLogin()
        }
    }

    private fun setObserver() {
        viewModel.isLoginSuccess.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finish()
                KeyBoardUtil.hide(requireActivity())
            }
        }
    }
}