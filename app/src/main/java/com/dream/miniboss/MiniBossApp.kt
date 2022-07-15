package com.dream.miniboss


import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.widget.RelativeLayout
import android.widget.Toast
import cn.jiguang.verifysdk.api.*
import cn.jiguang.verifysdk.api.JVerificationInterface.preLogin
import com.blankj.utilcode.util.ToastUtils
import com.dream.miniboss.login.LoginCodeActivity
import com.dream.miniboss.mine.ui.UserEditActivity
import com.dream.miniboss.task.*
import com.dream.miniboss.utils.LoginUIHelper
import com.dream.miniboss.utils.Preferences
import com.ruffian.library.widget.RTextView
import com.tcl.base.BaseApplication
import com.tcl.base.utils.startAppTime
import com.tcl.launcher.TaskDispatcher
import com.tcl.tclzjpro.task.InitEnvironment
import com.tencent.mmkv.MMKV


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
        //Preferences初始化
        Preferences.init(this);

        TaskDispatcher.createInstance()
            .addTask(InitTxSeriesTask())
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
                //loginAuth()
            }
        }
    }

    /**
     * 尝试一键登陆
     */
    private fun loginAuth() {
        //自定义界面
        val mRTextView = RTextView(this)
        mRTextView.text = "其他手机号码登录"
        val mLayoutParams1 = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        mLayoutParams1.setMargins(LoginUIHelper.dpToPx(124.0f), LoginUIHelper.dpToPx(385.0f), 0, 0)
        mRTextView.layoutParams = mLayoutParams1
        //增加底部隐私条款
        val list: MutableList<PrivacyBean> = ArrayList()
        val privacyBean1 = PrivacyBean("用户协议", "https://www.baidu.com", "、")
        val privacyBean2 = PrivacyBean("隐私政策", "https://www.baidu.com", "、")
        list.add(privacyBean1)
        list.add(privacyBean2)
        val jVerifyUIConfig = JVerifyUIConfig.Builder()
            .setPrivacyOffsetX(20)
            .setPrivacyState(true)
            .setNavColor(R.color.blue_light)
            .addCustomView(mRTextView, true) { context, view ->
                startActivity(Intent(this, LoginCodeActivity::class.java)
                )
            } //.setLogoImgPath("ic_launcher_background")
            .setPrivacyNameAndUrlBeanList(list)
            .enableHintToast(true, Toast.makeText(this, "请先同意页面底部的隐私条款", Toast.LENGTH_LONG))
            .build()
        JVerificationInterface.setCustomUIWithConfig(jVerifyUIConfig)
        //配置登录文件
        val settings = LoginSettings()
        settings.isAutoFinish = true //设置登录完成后是否自动关闭授权页
        settings.timeout = 6 * 1000 //设置超时时间，单位毫秒。 合法范围（0，30000],范围以外默认设置为10000
        settings.authPageEventListener = object : AuthPageEventListener() {
            override fun onEvent(cmd: Int, msg: String) {

                //do something...
            }
        } //设置授权页事件监听
        JVerificationInterface.loginAuth(this, settings) { code, content, operator ->
            if (code == 6000) {
                startActivity(Intent(this, UserEditActivity::class.java))
                ToastUtils.showShort("登录成功")
            } else {
            }
        }
    }



}

