package com.tcl.base.weiget.multistatepage


/**
 * @author : tiaozi
 * time : 2020/11/17 9:34
 */
fun interface OnNotifyListener<T : MultiState> {
    fun onNotify(multiState: T)
}