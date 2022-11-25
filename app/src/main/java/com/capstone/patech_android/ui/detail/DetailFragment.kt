package com.capstone.patech_android.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentDetailBinding
import com.capstone.patech_android.util.GraphAxisValueFormat
import com.capstone.patech_android.util.navigateWithData
import com.capstone.patech_android.util.popBackStack
import com.capstone.patech_android.util.timeFormatToCalender
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.util.*

class DetailFragment : ViewModelFragment<FragmentDetailBinding, DetailViewModel>(
    R.layout.fragment_detail
) {
    override val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    private lateinit var timelineAdapter: DetailTimelineAdapter

    private var chartData = ArrayList<Entry>()
    private var lineData = LineData()

    private lateinit var graphView: LineChart

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        graphView = binding.layoutGraph
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
        initChartUI()
        initChartData()
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

    private fun initChartUI() {
        val xAxis = graphView.xAxis
        xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            textSize = 13f
            textColor = getColorInt(R.color.black_sub)
            setDrawGridLines(true)
            gridColor = getColorInt(R.color.gray_light)
            gridLineWidth = 1f
            isGranularityEnabled = true
            setDrawAxisLine(false)
            valueFormatter = GraphAxisValueFormat()
        }

        graphView.apply {
            axisRight.isEnabled = false
            axisLeft.isEnabled = false
            legend.isEnabled = false
            description.isEnabled = false
            setPinchZoom(false)
            isDoubleTapToZoomEnabled = false
            setBackgroundColor(getColorInt(R.color.graph_bg))
            setExtraOffsets(26f, 0f, 26f, 24f)
            animateY(1000, Easing.EaseInOutBack)
        }
        graphView.data = lineData
    }

    private fun initChartData() {
        viewModel.graphDataList.observe(viewLifecycleOwner) { list ->
            if (list.isNotEmpty()) {
                for (item in list) {
                    val dateCalender = timeFormatToCalender(item.date)
                    if (dateCalender != null) {
                        val dateXAxis = (dateCalender.get(Calendar.DAY_OF_YEAR) - 1)
                        chartData.add(Entry(dateXAxis.toFloat(), item.weight))
                    }
                }

                val lineDataSetValue = LineDataSet(chartData, "weight")
                lineData = LineData(lineDataSetValue)

                with(lineDataSetValue) {
                    lineWidth = 2.toFloat()
                    color = getColorInt(R.color.green_main)
                    setCircleColor(getColorInt(R.color.green_main))
                    circleRadius = 5f
                    isHighlightEnabled = false
                    valueTextSize = 12f
                    valueTextColor = getColorInt(R.color.green_main)
                }
                graphView.data = lineData
                graphView.apply {
                    setVisibleXRangeMaximum(5f)
                    moveViewToX(data.xMax)
                }
            }
        }
    }

    private fun getColorInt(color: Int): Int {
        return resources.getColor(color, null)
    }
}