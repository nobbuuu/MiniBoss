package com.dream.miniboss.job.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.TextPaint
import android.widget.LinearLayout
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ResourceUtils
import com.dream.miniboss.R
import com.dream.miniboss.databinding.FragmentJobsBinding
import com.dream.miniboss.job.adapter.JobsAdapter
import com.dream.miniboss.job.bean.JobTypeBean
import com.dream.miniboss.job.bean.JobsItemBean
import com.dream.miniboss.job.vm.JobViewModel
import com.tcl.base.common.adapter.MyFragmentPagerAdapter
import com.tcl.base.common.ui.BaseFragment
import com.tcl.base.kt.ktClick
import com.tcl.base.kt.ktDpToPx
import com.tcl.base.kt.ktStartActivity
import com.tcl.base.weiget.recylerview.WaterFallItemDecoration
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView

/**
 *@author tiaozi
 *@date   2022/1/26
 *description
 */
class JobsFragment(val type: Int) : BaseFragment<JobViewModel, FragmentJobsBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        when (type) {
            1 -> {
                mBinding.searchLay.setBackgroundResource(R.mipmap.indicator1)
            }
            2 -> {
                mBinding.searchLay.setBackgroundResource(R.mipmap.indicator2)
            }
            3 -> {
                mBinding.searchLay.setBackgroundResource(R.mipmap.indicator3)
            }
            4 -> {
                mBinding.searchLay.setBackgroundResource(R.mipmap.indicator4)
            }
        }

        initMagicIndicator()
        initRv()

        listener()
    }

    fun listener() {
        mBinding.locationTv.ktClick {
            ktStartActivity(ThreeLevelSelectActivity::class)
        }
        mBinding.filterTv.ktClick {
            ktStartActivity(JobFilterActivity::class)
        }
    }

    private fun initRv() {
        val jobsAdapter = JobsAdapter()
        mBinding.commonRv.apply {
            adapter = jobsAdapter
            addItemDecoration(
                WaterFallItemDecoration(
                    ConvertUtils.dp2px(0f),
                    ConvertUtils.dp2px(16f)
                )
            )
        }

        val jobsData = arrayListOf<JobsItemBean>()
        repeat(10) {
            jobsData.add(JobsItemBean())
        }
        jobsAdapter.setList(jobsData)
    }

    private fun initMagicIndicator() {
        val mutableList = arrayListOf("推荐")
        var textWidthSum = 0f
        mutableList.forEach {
            textWidthSum += TextPaint().measureText(it)
        }
        val commonNavigator = CommonNavigator(requireContext())
        commonNavigator.isAdjustMode = false
        commonNavigator.isSkimOver = true
        commonNavigator.isFollowTouch = false
        commonNavigator.isEnablePivotScroll = true
        commonNavigator.isSmoothScroll = true

        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int = mutableList.size

            override fun getTitleView(context: Context?, index: Int): IPagerTitleView {
                val groupName = mutableList[index]
                val simplePagerTitleView = SimplePagerTitleView(context)
                var leftMargin = 0
                var rightMargin = 0
                if (mutableList.size > 5) {
                    leftMargin = 13.ktDpToPx
                    rightMargin = 13.ktDpToPx
                }
                if (index == 0) {
                    leftMargin = 0
                }
                if (index == mutableList.size - 1) {
                    rightMargin = 0
                }
                simplePagerTitleView.setPadding(leftMargin, 0, rightMargin, 0)
                simplePagerTitleView.text = groupName
                simplePagerTitleView.textSize = 14f
                simplePagerTitleView.normalColor = ColorUtils.getColor(R.color.colorBlack666)
                simplePagerTitleView.selectedColor = ColorUtils.getColor(R.color.main_text_color)

                simplePagerTitleView.setOnClickListener {
                    mBinding.magicIndicator.onPageSelected(index)
                }
                return simplePagerTitleView
            }

            override fun getIndicator(context: Context?): IPagerIndicator? {
                return null
            }
        }

        mBinding.magicIndicator.navigator = commonNavigator
        val titleContainer = commonNavigator.titleContainer // must after setNavigator
        titleContainer.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
        titleContainer.dividerPadding = 18.ktDpToPx

    }
}