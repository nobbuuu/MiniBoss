package com.dream.miniboss.mine.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;
import com.hjq.bar.TitleBar;

public class AboutUsActivity extends BaseActivity {
    TitleBar mTitleBar;


    @Override
    protected int initLayout() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initView() {
        mTitleBar=fvbi(R.id.title_about_us);
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
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}