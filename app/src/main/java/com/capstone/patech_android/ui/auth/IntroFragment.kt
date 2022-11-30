package com.capstone.patech_android.ui.auth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.capstone.patech_android.R
import com.capstone.patech_android.base.BindingFragment
import com.capstone.patech_android.data.SharedPreferenceController
import com.capstone.patech_android.databinding.FragmentAuthIntroBinding
import com.capstone.patech_android.ui.MainActivity
import com.capstone.patech_android.util.navigate

class IntroFragment : BindingFragment<FragmentAuthIntroBinding>(
    R.layout.fragment_auth_intro
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListener()
        checkAutoLogin()
    }

    private fun checkAutoLogin() {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                val token = SharedPreferenceController.getToken()
                if (token != null && token.isNotBlank()) {
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    requireActivity().finish()
                } else {
                    binding.splash.visibility = View.GONE
                    binding.layoutIntro.visibility = View.VISIBLE
                }
            }, 1500
        )
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