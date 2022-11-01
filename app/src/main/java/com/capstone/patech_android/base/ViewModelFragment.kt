package com.capstone.patech_android.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.capstone.patech_android.R

abstract class ViewModelFragment<VD : ViewDataBinding, VM : ViewModel>(
    @LayoutRes
    private val layoutResId: Int
) : Fragment() {
    private var _binding: VD? = null
    val binding get() = _binding ?: error(getString(R.string.binding_error))

    protected abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)

        return binding.also { viewDataBinding ->
            viewDataBinding.lifecycleOwner = this
            // viewDataBinding.setVariable(BR.viewModel, viewModel)
        }.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}