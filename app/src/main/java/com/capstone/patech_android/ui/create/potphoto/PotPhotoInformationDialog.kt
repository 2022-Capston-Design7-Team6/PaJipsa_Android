package com.capstone.patech_android.ui.create.potphoto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.capstone.patech_android.R
import com.capstone.patech_android.databinding.DialogPotPhotoInfomationBinding

class PotPhotoInformationDialog(
    private val doAfterConfirm: (rating: Boolean) -> Unit
) : DialogFragment() {
    lateinit var binding: DialogPotPhotoInfomationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogPotPhotoInfomationBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        initView()
        addListener()
        return binding.root
    }

    private fun initView() {
        requireNotNull(dialog).apply {
            requireNotNull(window).apply {
                setBackgroundDrawableResource(R.drawable.shape_white_fill_20)
            }
        }
    }

    private fun addListener() {
        binding.btnNext.setOnClickListener {
            doAfterConfirm(true)
            dismiss()
        }
    }
}