package com.dream.miniboss.job.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import coil.load
import com.dream.miniboss.R
import com.ruffian.library.widget.RImageView

class JobTypeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.item_job_type, this)

    }

    fun setData(imgUrl: String? = "", name: String? = ""): JobTypeView {
        val imgIv = findViewById<RImageView>(R.id.imgIv)
        val nameTv = findViewById<TextView>(R.id.nameTv)
        if (!imgUrl.isNullOrEmpty()){
            imgIv.load(imgUrl)
        }
        nameTv.text = name
        return this
    }
    fun setData(imgRes: Int? = 0, name: String? = ""): JobTypeView {
        val imgIv = findViewById<RImageView>(R.id.imgIv)
        val nameTv = findViewById<TextView>(R.id.nameTv)
        imgRes?.let {
            imgIv.load(it)
        }
        nameTv.text = name
        return this
    }
}