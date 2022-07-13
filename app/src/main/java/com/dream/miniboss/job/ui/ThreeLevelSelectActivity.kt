package com.dream.miniboss.job.ui

import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.ColorUtils
import com.dream.miniboss.R
import com.dream.miniboss.databinding.ActivitySelectThreeLevelBinding
import com.dream.miniboss.job.vm.SelectViewModel
import com.tcl.base.common.ui.BaseActivity

class ThreeLevelSelectActivity : BaseActivity<SelectViewModel, ActivitySelectThreeLevelBinding>() {

    override fun initStateBar(stateBarColor: Int, isLightMode: Boolean, fakeView: View?) {
        super.initStateBar(ColorUtils.getColor(R.color.white), true, mBinding.titleBar)
    }

    override fun initView(savedInstanceState: Bundle?) {


    }

    override fun initData() {

    }

    override fun initDataOnResume() {

    }
}