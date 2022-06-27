package com.dream.miniboss.mine.ui;

import android.Manifest;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;
import com.dream.miniboss.utils.PermissionsUtils;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 创建日期：2022-06-27 on 1:42
 * 描述:衣带渐宽终不悔、为伊消得人憔悴
 * 作者:HeGuiCun Administrator
 */
public class PermissionSettingActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {
    TitleBar mTitleBar;
    Switch localSwitch, calendarSwitch, cameraSwitch, voiceSwitch, deviceSwitch, albumSwitch;
    String[] permissionsLocal,permissionsCamera,permissionsVoice,permissionsCalendar,permissionsDevice;
    @Override
    protected int initLayout() {
        return R.layout.activity_setting_permission;
    }

    @Override
    protected void initView() {
        mTitleBar = fvbi(R.id.title_permission_setting);
        localSwitch = fvbi(R.id.switch_permission_local);
        calendarSwitch = fvbi(R.id.switch_permission_canlder);
        cameraSwitch = fvbi(R.id.switch_permission_camera);
        voiceSwitch = fvbi(R.id.switch_permission_voice);
        deviceSwitch = fvbi(R.id.switch_permission_device);
        albumSwitch = fvbi(R.id.switch_permission_album);
        //两个日历权限和一个数据读写权限
        permissionsLocal = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION
        };
        permissionsCamera=new String[]{Manifest.permission.CAMERA};
        permissionsVoice=new String[]{ Manifest.permission.ADD_VOICEMAIL};
        permissionsCalendar=new String[]{Manifest.permission.WRITE_CALENDAR,Manifest.permission.READ_CALENDAR};
        permissionsDevice=new String[]{Manifest.permission.READ_PHONE_STATE};
         PermissionsUtils.showSystemSetting = false;//是否支持显示系统设置权限设置窗口跳转
        //这里的this不是上下文，是Activity对象！

    }

    @Override
    protected void initData() {
        localSwitch.setOnCheckedChangeListener(this);
        calendarSwitch.setOnCheckedChangeListener(this);
        cameraSwitch.setOnCheckedChangeListener(this);
        voiceSwitch.setOnCheckedChangeListener(this);
        deviceSwitch.setOnCheckedChangeListener(this);
        albumSwitch.setOnCheckedChangeListener(this);

        event();
    }

    //点击事件
    private void event() {
        //设置顶部titlebar的返回按钮监听
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
        /**
         * 这里实现权限设置
         */

    }

    //以下就是打开监听权限的点击事件
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.switch_permission_local:
                if (localSwitch.isChecked()) {
                Log.d(TAG, "onCheckedChanged: " + "这是位置权限" +permissionsLocal);
                PermissionsUtils.getInstance().chekPermissions(this, permissionsLocal, permissionsResult);
                }else {
                    Toast.makeText(PermissionSettingActivity.this, "请滑动按钮申请权限!", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.switch_permission_canlder:
                if (calendarSwitch.isChecked()) {
                    Log.d(TAG, "onCheckedChanged: " + "日历权限" +permissionsCalendar);
                    PermissionsUtils.getInstance().chekPermissions(this, permissionsCalendar, permissionsResult);
                }else {
                    Toast.makeText(PermissionSettingActivity.this, "请滑动按钮申请权限!", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.switch_permission_camera:
                if (cameraSwitch.isChecked()) {
                    Log.d(TAG, "onCheckedChanged: " + "日历权限" +permissionsCamera);
                    PermissionsUtils.getInstance().chekPermissions(this, permissionsCamera, permissionsResult);
                }else {
                    Toast.makeText(PermissionSettingActivity.this, "请滑动按钮申请权限!", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.switch_permission_voice:
                if (voiceSwitch.isChecked()) {
                    Log.d(TAG, "onCheckedChanged: " + "日历权限" +permissionsVoice);
                    PermissionsUtils.getInstance().chekPermissions(this, permissionsVoice, permissionsResult);
                }else {
                    Toast.makeText(PermissionSettingActivity.this, "请滑动按钮申请权限!", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.switch_permission_device:
                if (deviceSwitch.isChecked()) {
                    Log.d(TAG, "onCheckedChanged: " + "日历权限" +permissionsDevice);
                    PermissionsUtils.getInstance().chekPermissions(this, permissionsDevice, permissionsResult);
                }else {
                    Toast.makeText(PermissionSettingActivity.this, "请滑动按钮申请权限!", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.switch_permission_album:
                if (albumSwitch.isChecked()) {
                    Log.d(TAG, "onCheckedChanged: " + "日历权限" +permissionsCamera);
                    PermissionsUtils.getInstance().chekPermissions(this, permissionsCamera, permissionsResult);
                }else {
                    Toast.makeText(PermissionSettingActivity.this, "请滑动按钮申请权限!", Toast.LENGTH_SHORT).show();

                }
                break;
            default:
        }

    }
    //创建监听权限的接口对象
    PermissionsUtils.IPermissionsResult permissionsResult = new PermissionsUtils.IPermissionsResult() {
        @Override
        public void passPermissons() {
            Toast.makeText(PermissionSettingActivity.this, "权限通过！", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void forbitPermissons() {
//            finish();
            Toast.makeText(PermissionSettingActivity.this, "权限不通过!", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //就多一个参数this
        PermissionsUtils.getInstance().onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

}
