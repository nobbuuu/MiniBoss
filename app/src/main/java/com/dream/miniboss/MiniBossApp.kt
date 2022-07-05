package com.dream.miniboss


import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.widget.RelativeLayout
import android.widget.Toast
import cn.jiguang.verifysdk.api.*
import cn.jiguang.verifysdk.api.JVerificationInterface.preLogin
import com.blankj.utilcode.util.ToastUtils
import com.dream.miniboss.login.LoginCodeActivity
import com.dream.miniboss.mine.ui.UserEditActivity
import com.dream.miniboss.task.CatchCrashTask
import com.dream.miniboss.task.ExceptionMonitorTask
import com.dream.miniboss.task.InitHttpClient
import com.dream.miniboss.task.InitOtherTask
import com.dream.miniboss.utils.LoginUIHelper
import com.ruffian.library.widget.RTextView
import com.tcl.base.BaseApplication
import com.tcl.base.utils.startAppTime
import com.tcl.launcher.TaskDispatcher
import com.tcl.tclzjpro.task.InitEnvironment
import com.tencent.mmkv.MMKV
import java.lang.ref.WeakReference


/**
 * Author: tiaozi
 * Date : 2021/6/3
 * Drc:
 */
lateinit var mApplication: Application

@Suppress("UNREACHABLE_CODE")
class MiniBossApp : BaseApplication() {

    lateinit var browserJsActivityStack: MutableList<Activity>
     private var context:Context?=null
    override fun onCreate() {
        super.onCreate()
        context=this
        mApplication = this
        browserJsActivityStack = mutableListOf()
        TaskDispatcher.init(this)
        //启动器进行异步初始化
        MMKV.initialize(this)

        TaskDispatcher.createInstance()
            .addTask(CatchCrashTask())
            .addTask(InitEnvironment())
            .addTask(InitHttpClient())
            .addTask(InitOtherTask())
            .addTask(ExceptionMonitorTask())
            .start()
        startAppTime = SystemClock.currentThreadTimeMillis()

        //初始化一键登陆
        JVerificationInterface.setDebugMode(true)
      initVerification()

    }


    fun getContext(): Context?{
        return context
    }

    private fun initVerification() {
        //初始化
        JVerificationInterface.init(this)
        { code, msg ->
            if (code == 8000) {
                preLogin()
            }
        }
    }

    //预取号
    private fun preLogin() {
        preLogin(this, 5000) { code, content ->
            if (code == 7000) {
//                loginAuth()
            }
        }
    }



}

