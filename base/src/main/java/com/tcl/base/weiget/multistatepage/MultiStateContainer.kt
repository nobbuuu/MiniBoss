package com.tcl.base.weiget.multistatepage

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.tcl.base.weiget.multistatepage.state.SuccessState

/**
 * @author : tiaozi
 * time : 2020/11/17 9:34
 */
@SuppressLint("ViewConstructor")
class MultiStateContainer : FrameLayout {

    var onRetryEventListener: OnRetryEventListener? = null

    private var originTargetView: View? = null

    private var lastState: String = ""

    private var statePool: MutableMap<Class<out MultiState>, MultiState> = mutableMapOf()

    private var animator = ValueAnimator.ofFloat(0.0f, 1.0f).apply {
        duration = MultiStatePage.config.alphaDuration
    }

    constructor(
        context: Context,
        originTargetView: View,
        onRetryEventListener: OnRetryEventListener? = null
    ) : this(context, null) {
        this.originTargetView = originTargetView
        this.onRetryEventListener = onRetryEventListener
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : this(context, attrs, 0)

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (originTargetView == null && childCount == 1) {
            originTargetView = getChildAt(0)
        }
    }

    fun initialization() {
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        addView(originTargetView, 0, layoutParams)
    }

    inline fun <reified T : MultiState> show(enableAnimator: Boolean = false, noinline notify: (T) -> Unit = {}) {
        show(T::class.java, enableAnimator, notify)
    }

    @JvmOverloads
    fun <T : MultiState> show(multiState: T, enableAnimator: Boolean = false, onNotifyListener: OnNotifyListener<T>? = null) {
        if (childCount == 0) {
            initialization()
        }
        if (childCount > 1) {
            removeViewAt(1)
        }
        if (multiState is SuccessState) {
            //如果上次展示的是SuccessState则跳过
            if (lastState != SuccessState::class.java.name) {
                originTargetView?.visibility = View.VISIBLE
                if (enableAnimator) originTargetView?.executeAnimator()
            }
        } else {
            originTargetView?.visibility = View.INVISIBLE
            val currentStateView = multiState.onCreateMultiStateView(context, LayoutInflater.from(context), this)
            multiState.onMultiStateViewCreate(currentStateView)
            val retryView = multiState.bindRetryView()
            if (multiState.enableReload()) {
                retryView?.setOnClickListener { onRetryEventListener?.onRetryEvent(this) }
            }
            addView(currentStateView)
            if (enableAnimator) currentStateView.executeAnimator()
            onNotifyListener?.onNotify(multiState)
        }
        //记录上次展示的state
        lastState = multiState.javaClass.name
    }

    @JvmOverloads
    fun <T : MultiState> show(clazz: Class<T>, enableAnimator: Boolean = false, onNotifyListener: OnNotifyListener<T>? = null) {
        findState(clazz)?.let { multiState ->
            show(multiState as T, enableAnimator, onNotifyListener)
        }
    }

    private fun <T : MultiState> findState(clazz: Class<T>): MultiState? {
        return if (statePool.containsKey(clazz)) {
            statePool[clazz]
        } else {
            val state = clazz.newInstance()
            statePool[clazz] = state
            state
        }
    }

    private fun View.executeAnimator() {
        this.clearAnimation()
        animator.addUpdateListener {
            this.alpha = it.animatedValue as Float
        }
        animator.start()
    }
}