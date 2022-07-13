package com.dream.miniboss.job.adapter

import android.widget.TextView
import com.blankj.utilcode.util.ColorUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.dream.miniboss.R
import com.dream.miniboss.bean.SelectInfoBean
import com.tcl.base.kt.ktClick

class RadiaAdapter :
        BaseQuickAdapter<SelectInfoBean, BaseViewHolder>(R.layout.item_select_view) {
        override fun convert(holder: BaseViewHolder, item: SelectInfoBean) {
            val itemTv = holder.getView<TextView>(R.id.selectTv)
            if (item.isCheck) {
                itemTv.setBackgroundResource(R.drawable.shape_select_check)
                itemTv.setTextColor(ColorUtils.getColor(R.color.white))
            } else {
                itemTv.setBackgroundResource(R.drawable.shape_select_normal)
                itemTv.setTextColor(ColorUtils.getColor(R.color.main_text_color))
            }
            itemTv.text = item.name
            itemTv.ktClick {
                data.forEach {
                    /*if (it.name == item.name) {
                        it.isCheck = !it.isCheck
                    } else {
                    }*/
                    it.isCheck = it.name == item.name
                }
                notifyDataSetChanged()
            }
        }
    }