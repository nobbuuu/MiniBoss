package com.dream.miniboss.mine.ui;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;


import com.dream.miniboss.R;

import com.dream.miniboss.base.BaseActivity;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

/**
 * 创建日期：2022-06-26 on 23:56
 * 描述:衣带渐宽终不悔、为伊消得人憔悴
 * 作者:HeGuiCun Administrator
 */
public class SystemSettingActivity extends BaseActivity implements View.OnClickListener {
    TitleBar mTitleBar;
    LinearLayout mLayoutPermission;
    @Override
    protected int initLayout() {
        return R.layout.actiivity_setting_system;
    }

    @Override
    protected void initView() {
      mTitleBar=fvbi(R.id.system_setting_titleBar);
      mLayoutPermission=fvbi(R.id.lv_permission);
    }

    @Override
    protected void initData() {
        mLayoutPermission.setOnClickListener(this);
        mTitleBar.setOnTitleBarListener(new OnTitleBarListener() {
        @Override
        public void onLeftClick(View view) {
            onBackPressed();
        }

        @Override
        public void onTitleClick(View view) {

        }

        @Override
        public void onRightClick(View view) {

        }
    });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lv_permission:
                Log.d(TAG, "onClick: "+"---------");
//                toClass(this,PermissionSettingActivity.class);
              Intent mIntent=new Intent();
              mIntent.setClass(this, PermissionSettingActivity.class);
              startActivity(mIntent);
                break;
        }

    }
}
