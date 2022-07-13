package com.dream.miniboss.widget

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dream.miniboss.R
import com.dream.miniboss.bean.SelectInfoBean
import com.dream.miniboss.job.adapter.RadiaAdapter

/**
 * 多行多列单选RadioGroup
 */
class CustomRadioGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RecyclerView(context, attrs) {

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
            val dataList = arrayListOf<SelectInfoBean>()
            array.forEach {
                dataList.add(SelectInfoBean(name = it))
            }
            mAdapter.setList(dataList)
        }
    }
}
