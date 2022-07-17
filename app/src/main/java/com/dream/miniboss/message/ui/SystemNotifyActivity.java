package com.dream.miniboss.message.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;
import com.hjq.bar.TitleBar;

public class SystemNotifyActivity extends BaseActivity {
       TitleBar mTitleBar;

    @Override
    protected int initLayout() {
        return R.layout.activity_system_notify;
    }

    @Override
    protected void initView() {
        mTitleBar=fvbi(R.id.title_system_bar);
    }

    @Override
    protected void initData() {
     event();
    }

    private void event() {
        mTitleBar.getLeftView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}