package com.dream.miniboss.widget

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.dream.miniboss.R
import com.dream.miniboss.bean.SelectInfoBean

class CustomRadiaGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RecyclerView(context, attrs) {

    init {

    }


    inner class RadiaAdapter : BaseQuickAdapter<SelectInfoBean,BaseViewHolder>(R.layout.bvp_layout){
        override fun convert(holder: BaseViewHolder, item: SelectInfoBean) {

        }

    }
}