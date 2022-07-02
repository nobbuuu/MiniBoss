package com.dream.miniboss.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;
import com.dream.miniboss.login.utils.SMSBroadcastReceiver;
import com.hjq.bar.TitleBar;
import com.ruffian.library.widget.RTextView;

public class LoginCodeNumActivity extends BaseActivity {
    RTextView mRTextView;
    CountDownTimer mCountDownTimer;
    TitleBar mTitleBar;
     SMSBroadcastReceiver mSMSBroadcastReceiver = new SMSBroadcastReceiver();
    @Override
    protected int initLayout() {
        return R.layout.activity_login_code_num;
    }

    @Override
    protected void initView() {
        requestSendSms();//请求短信倒计时时间
        mRTextView = fvbi(R.id.tv_countDown_timer);
        mTitleBar = fvbi(R.id.title_login_code);
        initReceiver();
    }

    /**
     * 动态初始化注册广播
     */
    private void initReceiver() {

        // 动态注册广播接收短信验证码
        IntentFilter intentFilter = new IntentFilter(SMSBroadcastReceiver.SMS_RECEIVED_ACTION);
        // 设置广播优先级
        intentFilter.setPriority(Integer.MAX_VALUE);
        registerReceiver(mSMSBroadcastReceiver, intentFilter);
        mSMSBroadcastReceiver.setOnReceiveSMSListener(new SMSBroadcastReceiver.OnReceiveSMSListener() {
            @Override
            public void onReceived(String message) {
               if (message!=null){
                   Toast.makeText(LoginCodeNumActivity.this,message,Toast.LENGTH_LONG).show();
               }

            }
        });
    }

    @Override
    protected void initData() {
        mRTextView.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View view) {
                requestSendSms();
            }
        });
        //返回按钮监听
        mTitleBar.getLeftView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void requestSendSms() {

        if (mCountDownTimer != null) {
            return;
        } else {
            mCountDownTimer = new CountDownTimer(60 * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    mRTextView.setText((millisUntilFinished / 1001) + "S后重新获取");
                }

                @Override
                public void onFinish() {
                    mCountDownTimer = null;
                    mRTextView.setText("重新获取验证码");
                    //requestSendSms(); // 重新调用这个方法可以再次进行倒计时不断循环
                }
            };
        }
        mCountDownTimer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mSMSBroadcastReceiver);
    }
}