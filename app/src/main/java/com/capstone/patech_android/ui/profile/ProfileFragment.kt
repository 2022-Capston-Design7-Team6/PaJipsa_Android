package com.capstone.patech_android.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.capstone.patech_android.R
import com.capstone.patech_android.base.BindingFragment
import com.capstone.patech_android.data.SharedPreferenceController
import com.capstone.patech_android.databinding.FragmentProfileBinding
import com.capstone.patech_android.ui.auth.AuthActivity
import com.capstone.patech_android.ui.dialog.TwoButtonDialog
import com.capstone.patech_android.util.navigate
import com.capstone.patech_android.util.popBackStack

class ProfileFragment : BindingFragment<FragmentProfileBinding>(
    R.layout.fragment_profile
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListener()
    }

    private fun addListener() {
        binding.btnBack.setOnClickListener {
            popBackStack()
        }
        binding.layoutModifyNickname.setOnClickListener {
            navigate(R.id.action_profileFragment_to_renameFragment)
        }
        binding.layoutLogout.setOnClickListener {
            TwoButtonDialog(2) {
                SharedPreferenceController.clearSdf(requireContext())
                startActivity(Intent(requireContext(), AuthActivity::class.java))
                requireActivity().finish()
            }.show(childFragmentManager, "LOGOUT")
        }
    }
}