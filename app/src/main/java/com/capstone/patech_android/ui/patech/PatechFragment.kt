package com.capstone.patech_android.ui.patech

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentPatechBinding
import com.capstone.patech_android.util.popBackStack

class PatechFragment : ViewModelFragment<FragmentPatechBinding, PatechViewModel>(
    R.layout.fragment_patech
) {
    override val viewModel: PatechViewModel by viewModels()

    private lateinit var patechRankAdapter: PatechRankAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        patechRankAdapter = PatechRankAdapter()
        viewModel.fetchRankList()
        initRankRVAdapter()
        setRankList()
        addListener()
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