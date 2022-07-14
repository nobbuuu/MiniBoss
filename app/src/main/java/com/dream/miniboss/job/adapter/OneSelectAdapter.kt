package com.dream.miniboss.job.adapter

import android.widget.RelativeLayout
import com.blankj.utilcode.util.ColorUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.dream.miniboss.R
import com.dream.miniboss.bean.AddressAreaBean
import com.dream.miniboss.job.bean.JobsItemBean
import com.tcl.base.kt.ktClick

class OneSelectAdapter :
    BaseQuickAdapter<AddressAreaBean, BaseViewHolder>(R.layout.item_select_content) {

    override fun convert(holder: BaseViewHolder, item: AddressAreaBean) {

        holder.setText(R.id.nameTv, item.name)
        val itemRl = holder.getView<RelativeLayout>(R.id.itemRl)
        if (item.isSelect) {
            holder.setGone(R.id.curIv, false)
            holder.setTextColor(R.id.nameTv, ColorUtils.getColor(R.color.green))
            itemRl.setBackgroundColor(ColorUtils.getColor(R.color.white))
        } else {
            holder.setGone(R.id.curIv, true)
            holder.setTextColor(R.id.nameTv, ColorUtils.getColor(R.color.color_787878))
            itemRl.setBackgroundColor(ColorUtils.getColor(R.color.default_bg))
        }
        holder.itemView.ktClick {
            data.forEach {
                it.isSelect = it.name == item.name
            }
            notifyDataSetChanged()
        }
    }
}