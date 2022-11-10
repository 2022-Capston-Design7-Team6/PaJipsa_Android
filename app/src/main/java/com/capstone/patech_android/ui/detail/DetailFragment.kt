package com.capstone.patech_android.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentDetailBinding
import com.capstone.patech_android.util.popBackStack

class DetailFragment : ViewModelFragment<FragmentDetailBinding, DetailViewModel>(
    R.layout.fragment_detail
) {
    override val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    private lateinit var timelineAdapter: DetailTimelineAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        timelineAdapter = DetailTimelineAdapter()
        viewModel.fetchTimelineList()
        initRVAdapter()
        setTimelineList()
        addListener()
    }

    private fun initRVAdapter() {
        binding.rvTimeline.adapter = timelineAdapter
    }

    private fun setTimelineList() {
        viewModel.timelineList.observe(viewLifecycleOwner) { list ->
            list?.let {
                with(timelineAdapter) { submitList(list) }
            }
        }
    }

    private fun addListener() {
        binding.btnBack.setOnClickListener {
            popBackStack()
        }
    }
}