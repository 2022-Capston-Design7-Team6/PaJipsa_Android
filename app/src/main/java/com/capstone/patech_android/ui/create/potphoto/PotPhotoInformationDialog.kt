package com.capstone.patech_android.ui.create.potphoto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.capstone.patech_android.databinding.DialogPotPhotoInfomationBinding

class PotPhotoInformationDialog : DialogFragment() {
    lateinit var binding: DialogPotPhotoInfomationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogPotPhotoInfomationBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}