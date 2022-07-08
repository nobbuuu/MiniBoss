package com.dream.miniboss.mine.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;
import com.hjq.bar.TitleBar;

public class PersonAuthNameActivity extends BaseActivity {
      TitleBar mTitleBar;

    @Override
    protected int initLayout() {
        return R.layout.activity_person_auth_name;
    }

    @Override
    protected void initView() {
    mTitleBar=fvbi(R.id.title_Authy_person);
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