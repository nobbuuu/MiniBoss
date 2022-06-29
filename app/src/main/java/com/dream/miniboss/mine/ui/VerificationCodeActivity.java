package com.dream.miniboss.mine.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;

public class VerificationCodeActivity extends BaseActivity {


    @Override
    protected int initLayout() {
        return R.layout.activity_verification_code;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    /**
     * 点击标题返回按钮
     * @param view
     */
    public void GoBackActivity(View view) {
        onBackPressed();
    }
}