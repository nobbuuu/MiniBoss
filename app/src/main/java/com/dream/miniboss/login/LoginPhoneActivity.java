package com.dream.miniboss.login;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;
import com.dream.miniboss.main.MainActivity;
import com.dream.miniboss.mine.ui.UserEditActivity;

import java.util.ArrayList;
import java.util.List;

import cn.jiguang.verifysdk.api.AuthPageEventListener;
import cn.jiguang.verifysdk.api.JVerificationInterface;
import cn.jiguang.verifysdk.api.JVerifyUIConfig;
import cn.jiguang.verifysdk.api.LoginSettings;
import cn.jiguang.verifysdk.api.PreLoginListener;
import cn.jiguang.verifysdk.api.PrivacyBean;
import cn.jiguang.verifysdk.api.RequestCallback;
import cn.jiguang.verifysdk.api.VerifyListener;

public class LoginPhoneActivity extends BaseActivity {


    @Override
    protected int initLayout() {
        return R.layout.activity_login_phone;
    }

    @Override
    protected void initView() {

        JVerificationInterface.setDebugMode(true);
        initVerification();

    }
    @Override
    protected void initData() {

    }

    private void initVerification() {
        //初始化
        JVerificationInterface.init(this, new RequestCallback<String>() {
            @Override
            public void onResult(int code, String msg) {
                if (code == 8000) {
                    preLogin();
                }
            }
        });
    }



    //预取号
    private void preLogin() {
        JVerificationInterface.preLogin(this, 5000, new PreLoginListener() {
            @Override
            public void onResult(final int code, final String content) {
                if (code == 7000) {
                    loginAuth();

                }

            }
        });
    }

    //尝试登录
    private void loginAuth() {

        List<PrivacyBean> list = new ArrayList<>();
        PrivacyBean privacyBean1 = new PrivacyBean("用户协议", "https://www.baidu.com", "、");
        PrivacyBean privacyBean2 = new PrivacyBean("隐私政策", "https://www.baidu.com", "、");
        list.add(privacyBean1);
        list.add(privacyBean2);
        JVerifyUIConfig jVerifyUIConfig = new JVerifyUIConfig.Builder()
                .setPrivacyOffsetX(20)
                .setPrivacyState(true)
                .setNavColor(R.color.blue_light)

                //.setLogoImgPath("ic_launcher_background")
                .setPrivacyNameAndUrlBeanList(list)
                .enableHintToast(true, Toast.makeText(this, "请先同意页面底部的隐私条款", Toast.LENGTH_LONG))
                .build();
        JVerificationInterface.setCustomUIWithConfig(jVerifyUIConfig);

        LoginSettings settings = new LoginSettings();
        settings.setAutoFinish(true);//设置登录完成后是否自动关闭授权页
        settings.setTimeout(6 * 1000);//设置超时时间，单位毫秒。 合法范围（0，30000],范围以外默认设置为10000
        settings.setAuthPageEventListener(new AuthPageEventListener() {
            @Override
            public void onEvent(int cmd, String msg) {

                //do something...
            }
        });//设置授权页事件监听
        JVerificationInterface.loginAuth(this, settings, new VerifyListener() {
            @Override
            public void onResult(int code, String content, String operator) {
                if (code == 6000){

                    startActivity(new Intent(LoginPhoneActivity.this, UserEditActivity.class));
                    ToastUtils.showShort("登录成功");

                }else{

                }
            }
        });
    }

}