package com.dream.miniboss.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;

public class LoginCodeActivity extends BaseActivity {
    Button getCodeButton;
   ImageView mView;

    @Override
    protected int initLayout() {
        return R.layout.activity_login_code;
    }

    @Override
    protected void initView() {
        getCodeButton=fvbi(R.id.btn_get_code);
        mView=fvbi(R.id.iv_gouxuan);
    }

    @Override
    protected void initData() {
     getCodeButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             startActivity(new Intent(LoginCodeActivity.this,LoginCodeNumActivity.class));
         }
     });
     //勾选按钮的选择
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.setImageResource(R.mipmap.gouxuan);
            }
        });

    }
}