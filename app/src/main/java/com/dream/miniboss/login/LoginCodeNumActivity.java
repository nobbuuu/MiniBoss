package com.dream.miniboss.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;
import com.hjq.bar.TitleBar;
import com.ruffian.library.widget.RTextView;

public class LoginCodeNumActivity extends BaseActivity {
    RTextView mRTextView;
    CountDownTimer mCountDownTimer;
    TitleBar mTitleBar;

    @Override
    protected int initLayout() {
        return R.layout.activity_login_code_num;
    }

    @Override
    protected void initView() {
        requestSendSms();
        mRTextView = fvbi(R.id.tv_countDown_timer);
        mTitleBar = fvbi(R.id.title_login_code);
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
}