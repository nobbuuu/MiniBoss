package com.dream.miniboss.job.ui

import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.ColorUtils
import com.dream.miniboss.R
import com.dream.miniboss.databinding.ActivitySelectThreeLevelBinding
import com.dream.miniboss.job.adapter.OneSelectAdapter
import com.dream.miniboss.job.adapter.TTSelectAdapter
import com.dream.miniboss.job.vm.SelectViewModel
import com.tcl.base.common.ui.BaseActivity

class AddressSelectActivity : BaseActivity<SelectViewModel, ActivitySelectThreeLevelBinding>() {

    override fun initStateBar(stateBarColor: Int, isLightMode: Boolean, fakeView: View?) {
        super.initStateBar(ColorUtils.getColor(R.color.white), true, mBinding.titleBar)
    }

    private lateinit var oneAdapter: OneSelectAdapter
    private lateinit var twoAdapter: TTSelectAdapter
    private lateinit var threeAdapter: TTSelectAdapter
    override fun initView(savedInstanceState: Bundle?) {
        oneAdapter = OneSelectAdapter {
            twoAdapter.setList(it.areaBaseList)
        }
        twoAdapter = TTSelectAdapter {
            threeAdapter.setList(it.areaBaseList)
        }
        threeAdapter = TTSelectAdapter {
            //具体的地址

        }
        mBinding.oneRv.adapter = oneAdapter
        mBinding.twoRv.adapter = twoAdapter
        mBinding.threeRv.adapter = threeAdapter

    }

    override fun initData() {

        viewModel.getAddress()
    }

    override fun initDataOnResume() {

    }

    override fun startObserve() {
        super.startObserve()
        viewModel.addressData.observe(this) {
            oneAdapter.setList(it)
        }
    }
}