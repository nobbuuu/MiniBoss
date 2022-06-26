package com.dream.miniboss.mine

import android.os.Bundle
import com.dream.miniboss.databinding.FragmentMineBinding
import com.dream.miniboss.mine.vm.MineViewModel
import com.tcl.base.common.ui.BaseFragment
import com.tcl.base.kt.ktClick

/**
 *@author tiaozi
 *@date   2022/1/26
 *description
 */
class MineFragment : BaseFragment<MineViewModel,FragmentMineBinding>() {
    override fun initView(savedInstanceState: Bundle?) {
        mBinding.title.ktClick {

        }
    }
}