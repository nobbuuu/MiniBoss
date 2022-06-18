package com.dream.miniboss.task

import com.dream.miniboss.other.CrashHandler
import com.tcl.launcher.task.MainTask


class CatchCrashTask : MainTask() {

    override fun run() {
        CrashHandler.openCatchCrash(true)//如果不是生产环境开关打开
        CrashHandler.init()//初始化blankj的CrashUtils
    }
}