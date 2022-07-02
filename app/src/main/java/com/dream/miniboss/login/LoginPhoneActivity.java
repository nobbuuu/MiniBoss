package com.dream.miniboss.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;

public class LoginPhoneActivity extends BaseActivity {


    @Override
    protected int initLayout() {
        return R.layout.activity_login_phone;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    public void OtherLogin(View view) {
        startActivity(new Intent(this,LoginCodeActivity.class));
    }
}