package com.dream.miniboss.job

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.blankj.utilcode.util.BarUtils
import com.dream.miniboss.databinding.FragmentJobMainBinding
import com.dream.miniboss.job.bean.JobTypeBean
import com.dream.miniboss.job.ui.JobTypeView
import com.dream.miniboss.job.ui.JobsFragment
import com.dream.miniboss.job.vm.JobViewModel
import com.google.android.material.tabs.TabLayout
import com.tcl.base.common.adapter.MyFragmentPagerAdapter
import com.tcl.base.common.ui.BaseFragment

/**
 *@author tiaozi
 *@date   2022/1/26
 *description
 */
class JobMainFragment : BaseFragment<JobViewModel, FragmentJobMainBinding>() {
    private val tabTitles = arrayOf("普工技工", "兼职注册师", "技术服务", "经营管理")

    override fun initView(savedInstanceState: Bundle?) {
        mBinding.appNameTv.setPadding(0, BarUtils.getStatusBarHeight(), 0, 0)
        mBinding.topBg.setPadding(0, BarUtils.getStatusBarHeight(), 0, 0)
        initTabAndVp()
    }

    private fun initTabAndVp() {
        val fragments = arrayListOf<Fragment>()
        val jobTypeData = arrayListOf<JobTypeBean>()
        tabTitles.forEachIndexed { index, s ->
            mBinding.tabLayout.addTab(mBinding.tabLayout.newTab())
            fragments.add(JobsFragment(index + 1))
            jobTypeData.add(JobTypeBean(index + 1, name = s))
        }
        mBinding.homeVp.adapter = MyFragmentPagerAdapter(requireActivity(), fragments)
        mBinding.homeVp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mBinding.tabLayout.selectTab(mBinding.tabLayout.getTabAt(position))
            }
        })

        repeat(mBinding.tabLayout.tabCount) {
            val tab = mBinding.tabLayout.getTabAt(it)
            tab?.customView =
                JobTypeView(requireContext()).setData(jobTypeData[it].img, jobTypeData[it].name)
        }
        mBinding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                mBinding.homeVp.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}