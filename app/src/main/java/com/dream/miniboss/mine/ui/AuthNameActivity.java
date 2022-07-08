package com.dream.miniboss.mine.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;
import com.hjq.bar.TitleBar;
import com.ruffian.library.widget.RTextView;

public class AuthNameActivity extends BaseActivity {
     TitleBar mTitleBar;
     RTextView mAuthyName;
    @Override
    protected int initLayout() {
        return R.layout.activity_authenty_name;
    }

    @Override
    protected void initView() {
        mTitleBar=fvbi(R.id.title_authy_name);
        mAuthyName=fvbi(R.id.tvAuthyName);

    }

    @Override
    protected void initData() {
     event();
    }
    //点击返回按钮
    private void event() {
        mTitleBar.getLeftView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //点击实名认证
        mAuthyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AuthNameActivity.this,PersonAuthNameActivity.class));
            }
        });
    }

}