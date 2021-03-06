package com.dream.miniboss.job.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.dream.miniboss.R
import com.dream.miniboss.bean.SelectInfoBean
import com.ruffian.library.widget.RCheckBox
import com.tcl.base.kt.ktClick

class RadiaAdapter :
    BaseQuickAdapter<SelectInfoBean, BaseViewHolder>(R.layout.item_select_view) {
    override fun convert(holder: BaseViewHolder, item: SelectInfoBean) {
        val itemTv = holder.getView<RCheckBox>(R.id.selectTv)
        itemTv.text = item.name
        itemTv.isChecked = item.isCheck
        itemTv.ktClick {
            data.forEach {
                if (it.name == item.name) {
                    it.isCheck = !it.isCheck
                } else {
                    it.isCheck = false
                }
            }
            notifyDataSetChanged()
        }
    }
}