package com.dream.miniboss.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;
import com.hjq.bar.TitleBar;

public class LoginCodeActivity extends BaseActivity {
     TitleBar mTitle;
    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
     mTitle=fvbi(R.id.title_login_code);
    }

    @Override
    protected void initData() {
    mTitle.getLeftIcon().isVisible();
    }
}