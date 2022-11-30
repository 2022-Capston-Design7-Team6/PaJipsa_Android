package com.capstone.patech_android.ui.auth

import android.os.Bundle
import android.view.View
import com.capstone.patech_android.R
import com.capstone.patech_android.base.BindingFragment
import com.capstone.patech_android.databinding.FragmentAuthIntroBinding
import com.capstone.patech_android.util.navigate

class IntroFragment : BindingFragment<FragmentAuthIntroBinding>(
    R.layout.fragment_auth_intro
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListener()
    }

    private fun addListener() {
        binding.btnLogin.setOnClickListener {
            navigate(R.id.action_introFragment_to_loginFragment)
        }
        binding.layoutRegister.setOnClickListener {
            navigate(R.id.action_introFragment_to_registerFragment)
        }
    }
}