package com.dream.miniboss.mine.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;
import com.hjq.bar.TitleBar;

public class MeritRecordingActivity extends BaseActivity {
    TitleBar mTitleBar;

    @Override
    protected int initLayout() {
        return R.layout.activity_merit_recording;
    }

    @Override
    protected void initView() {
      mTitleBar=fvbi(R.id.merit_record_title);
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