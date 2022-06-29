package com.dream.miniboss.mine.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;
import com.hjq.bar.TitleBar;

public class PhoneNumberActivity extends BaseActivity {
      TitleBar mTitle;

    @Override
    protected int initLayout() {
        return R.layout.activity_phone_number;
    }

    @Override
    protected void initView() {
    mTitle=fvbi(R.id.title_user_number);
    }

    @Override
    protected void initData() {
     event();
    }

    private void event() {
        mTitle.getLeftView().setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View view) {
                onBackPressed();
            }
        });
    }
}