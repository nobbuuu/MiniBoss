package com.dream.miniboss.mine.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;
import com.ruffian.library.widget.RTextView;

public class VerificationCodeActivity extends BaseActivity {
    RTextView mCountTimer;
    private CountDownTimer mCountDownTimer;

    @Override
    protected int initLayout() {
        return R.layout.activity_verification_code;
    }

    @Override
    protected void initView() {
        requestSendSms();
        mCountTimer = fvbi(R.id.tv_count_timer);
    }

    @Override
    protected void initData() {
    mCountTimer.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            requestSendSms();
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
                    mCountTimer.setText((millisUntilFinished / 1001) + "S后重新获取");
                }

                @Override
                public void onFinish() {
                    mCountDownTimer = null;
                    mCountTimer.setText("重新获取验证码");
                    //requestSendSms(); // 重新调用这个方法可以再次进行倒计时不断循环
                }
            };
        }
        mCountDownTimer.start();
    }
    /**
     * 点击标题返回按钮
     * @param view
     */
    public void GoBackActivity (View view){
        onBackPressed();
    }
}