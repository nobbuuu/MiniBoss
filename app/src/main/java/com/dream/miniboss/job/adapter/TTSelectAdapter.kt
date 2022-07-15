package com.dream.miniboss.job.adapter

import com.blankj.utilcode.util.ColorUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.dream.miniboss.R
import com.dream.miniboss.bean.AddressAreaBean
import com.tcl.base.kt.ktClick

class TTSelectAdapter(val block: ((AddressAreaBean) -> Unit)? = null) :
    BaseQuickAdapter<AddressAreaBean, BaseViewHolder>(R.layout.item_select_tt) {

    override fun convert(holder: BaseViewHolder, item: AddressAreaBean) {

        holder.setText(R.id.nameTv, item.name)
        if (item.isSelect) {
            holder.setTextColor(R.id.nameTv, ColorUtils.getColor(R.color.green))
        } else {
            holder.setTextColor(R.id.nameTv, ColorUtils.getColor(R.color.color_787878))
        }
        holder.itemView.ktClick {
            data.forEach {
                it.isSelect = it.name == item.name
            }
            notifyDataSetChanged()
            block?.invoke(item)
        }
    }
}