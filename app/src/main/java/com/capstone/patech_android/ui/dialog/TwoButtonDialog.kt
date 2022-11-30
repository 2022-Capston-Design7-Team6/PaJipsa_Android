package com.capstone.patech_android.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.capstone.patech_android.R
import com.capstone.patech_android.databinding.DialogTwoButtonBinding

class TwoButtonDialog(
    private val dialogMode: Int,
    private val doAfterConfirm: () -> Unit
) : DialogFragment() {
    private lateinit var binding: DialogTwoButtonBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogTwoButtonBinding.inflate(layoutInflater, container, false)
        initView()
        setClickListener()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setLayout()
    }

    private fun setLayout() {
        val dialogWidth = (resources.displayMetrics.widthPixels * 0.83).toInt()

        requireNotNull(dialog).apply {
            requireNotNull(window).apply {
                setLayout(
                    dialogWidth,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                setBackgroundDrawableResource(R.drawable.shape_white_fill_20)
            }
        }
    }

    private fun initView() {
        when (dialogMode) {
            PLANT_DELETE -> setText(
                title = "선택한 파를 삭제할까요?",
                titleSub = "해당 파의 파테크 기록이 모두 사라집니다.",
                confirm = "삭제"
            )
            WITHDRAWAL -> setText(
                title = "계정을 삭제하시겠습니까?",
                titleSub = "파테크 정보가 모두 사라집니다.",
                confirm = "탈퇴하기"
            )
            LOGOUT -> setText(
                title = "로그아웃 하시겠습니까?",
                confirm = "로그아웃"
            )
        }
    }

    private fun setText(title: String, titleSub: String, confirm: String) {
        binding.tvTitle.text = title
        binding.tvTitleSub.text = titleSub
        binding.tvConfirm.text = confirm
    }

    private fun setText(title: String, confirm: String) {
        binding.tvTitle.text = title
        binding.tvConfirm.text = confirm
        binding.tvTitleSub.visibility = View.GONE
    }

    private fun setClickListener() {
        binding.tvConfirm.setOnClickListener {
            doAfterConfirm()
            dismiss()
        }

        binding.tvCancel.setOnClickListener {
            dismiss()
        }
    }

    companion object {
        const val PLANT_DELETE = 0
        const val WITHDRAWAL = 1
        const val LOGOUT = 2
    }
}