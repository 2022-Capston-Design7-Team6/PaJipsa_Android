package com.capstone.patech_android.ui.patech

import android.os.Bundle
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import androidx.fragment.app.viewModels
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentPatechBinding
import com.capstone.patech_android.util.popBackStack
import java.text.SimpleDateFormat
import java.util.*

class PatechFragment : ViewModelFragment<FragmentPatechBinding, PatechViewModel>(
    R.layout.fragment_patech
) {
    override val viewModel: PatechViewModel by viewModels()

    private lateinit var patechRankAdapter: PatechRankAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        patechRankAdapter = PatechRankAdapter()
        viewModel.fetchRankData()
        initTodayDate()
        setPatechValueText()
        initRankRVAdapter()
        setRankList()
        addListener()
    }

    private fun initTodayDate() {
        val simpleDateFormat = SimpleDateFormat("MM.dd", Locale.KOREA)
        binding.tvNowDate.text = simpleDateFormat.format(Calendar.getInstance().time)
    }

    private fun setPatechValueText() {
        viewModel.patechValue.observe(viewLifecycleOwner) {
            if (it.isNotBlank()) {
                binding.tvValue.text = buildSpannedString {
                    append(it)
                    inSpans(
                        ForegroundColorSpan(requireContext().getColor(R.color.black)),
                    ) {
                        append("만큼을 수확했어요!")
                    }
                }
            }
        }
    }

    private fun initRankRVAdapter() {
        binding.rvRank.adapter = patechRankAdapter
    }

    private fun setRankList() {
        viewModel.rankList.observe(viewLifecycleOwner) { list ->
            list?.let {
                with(patechRankAdapter) { submitList(list) }
            }
        }
    }

    private fun addListener() {
        binding.btnBack.setOnClickListener {
            popBackStack()
        }
    }
}