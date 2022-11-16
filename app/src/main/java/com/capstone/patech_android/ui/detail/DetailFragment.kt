package com.capstone.patech_android.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentDetailBinding
import com.capstone.patech_android.util.navigateWithData
import com.capstone.patech_android.util.popBackStack
import com.capstone.patech_android.util.timeFormatToCalender
import java.util.*

class DetailFragment : ViewModelFragment<FragmentDetailBinding, DetailViewModel>(
    R.layout.fragment_detail
) {
    override val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    private lateinit var timelineAdapter: DetailTimelineAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        timelineAdapter = DetailTimelineAdapter()
        viewModel.fetchDetailData(args.plantId)
        viewModel.setPlantInfoDate(
            args.plantName,
            args.plantCategory,
        )
        addListener(args.plantId)
        initRVAdapter()
        setTimelineList()
        setHarvestTimeText()
        setBirthDateText()
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

    private fun addListener(plantId: Int) {
        binding.btnBack.setOnClickListener {
            popBackStack()
        }
        binding.btnHarvest.setOnClickListener {
            navigateWithData(
                DetailFragmentDirections.actionDetailFragmentToHarvestFragment(plantId)
            )
        }
        binding.btnWrite.setOnClickListener {
            navigateWithData(
                DetailFragmentDirections.actionDetailFragmentToRecordFragment(plantId)
            )
        }
    }


    private fun setBirthDateText() {
        viewModel.dateString.observe(viewLifecycleOwner) {
            binding.tvBirth.text = it
        }
    }

    private fun setHarvestTimeText() {
        viewModel.plantHarvest.observe(viewLifecycleOwner) {
            if (it == null) {
                binding.tvHarvest.text = "더 많은 파 일지가 필요해요!"
            } else {
                val time = timeFormatToCalender(it)
                if (time != null) {
                    val month = time.get(Calendar.MONTH) + 1
                    val date = time.get(Calendar.DATE)
                    val string = "${month}월 ${date}일"
                    binding.tvHarvest.text = string
                }
            }
        }
    }
}