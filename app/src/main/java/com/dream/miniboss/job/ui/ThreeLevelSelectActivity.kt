package com.dream.miniboss.job.ui

import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.ColorUtils
import com.dream.miniboss.R
import com.dream.miniboss.databinding.ActivitySelectThreeLevelBinding
import com.dream.miniboss.job.adapter.OneSelectAdapter
import com.dream.miniboss.job.vm.SelectViewModel
import com.tcl.base.common.ui.BaseActivity

class ThreeLevelSelectActivity : BaseActivity<SelectViewModel, ActivitySelectThreeLevelBinding>() {

    override fun initStateBar(stateBarColor: Int, isLightMode: Boolean, fakeView: View?) {
        super.initStateBar(ColorUtils.getColor(R.color.white), true, mBinding.titleBar)
    }

    private val oneAdapter = OneSelectAdapter()
    override fun initView(savedInstanceState: Bundle?) {

        mBinding.oneRv.apply {
            adapter = oneAdapter
        }

    }

    override fun initData() {

        viewModel.getAddress()
    }

    override fun initDataOnResume() {

    }

    override fun startObserve() {
        super.startObserve()
        viewModel.addressData.observe(this){
            oneAdapter.setList(it)
        }
    }
}