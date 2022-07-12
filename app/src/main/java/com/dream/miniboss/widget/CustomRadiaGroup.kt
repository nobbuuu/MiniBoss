package com.dream.miniboss.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ColorUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.dream.miniboss.R
import com.dream.miniboss.bean.SelectInfoBean
import com.ruffian.library.widget.RTextView

class CustomRadiaGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RecyclerView(context, attrs) {

    private val dataList = arrayListOf<SelectInfoBean>()
    private val mAdapter = RadiaAdapter()

    init {
        val array = context.obtainStyledAttributes(attrs, R.styleable.SelectView)
        adapter = mAdapter
        val columnNum = array.getInteger(R.styleable.SelectView_columnCount, 1)
        layoutManager = GridLayoutManager(context, columnNum)
        val arrayId = array.getResourceId(R.styleable.SelectView_arrayText, 0)
        if (arrayId > 0) {
            setArrayText(context.resources.getStringArray(arrayId))
        }

    }

    fun setArrayText(array: Array<String>?) {
        if (array != null) {
            dataList.clear()
            array.forEach {
                dataList.add(SelectInfoBean(name = it))
            }
            mAdapter.setList(dataList)
        }
    }

    inner class RadiaAdapter :
        BaseQuickAdapter<SelectInfoBean, BaseViewHolder>(R.layout.item_select_view) {
        override fun convert(holder: BaseViewHolder, item: SelectInfoBean) {
            val itemTv = holder.getView<RTextView>(R.id.selectTv)
            if (item.isCheck) {
                itemTv.helper.backgroundColorNormal = ColorUtils.getColor(R.color.green)
            } else {
                itemTv.helper.backgroundColorNormal = ColorUtils.getColor(R.color.default_bg)
            }
        }
    }
}
