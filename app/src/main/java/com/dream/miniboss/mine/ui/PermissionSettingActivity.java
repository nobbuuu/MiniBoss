package com.dream.miniboss.mine.ui;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
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
    //设置一个存储状态
    final boolean falg = true;
    SharedPreferences preferences;
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
        permissionsVoice=new String[]{ Manifest.permission.ADD_VOICEMAIL,Manifest.permission.RECORD_AUDIO};
        permissionsCalendar=new String[]{Manifest.permission.WRITE_CALENDAR,Manifest.permission.READ_CALENDAR};
        permissionsDevice=new String[]{Manifest.permission.READ_PHONE_STATE};
         PermissionsUtils.showSystemSetting = false;//是否支持显示系统设置权限设置窗口跳转
        //这里的this不是上下文，是Activity对象！
        // 从SharedPreferences获取数据:显示位置权限状态
        preferences = getSharedPreferences("local", Context.MODE_PRIVATE);
        if (preferences != null) {
            boolean name = preferences.getBoolean("flag", falg);
            localSwitch.setChecked(name);
        }
        //从SharedPreferences获取数据:显示日历权限状态
        preferences = getSharedPreferences("calendar", Context.MODE_PRIVATE);
        if (preferences != null) {
            boolean name = preferences.getBoolean("flag", falg);
            calendarSwitch.setChecked(name);
        }
        //从SharedPreferences获取数据:显示相机权限状态
        preferences = getSharedPreferences("camera", Context.MODE_PRIVATE);
        if (preferences != null) {
            boolean name = preferences.getBoolean("flag", falg);
            cameraSwitch.setChecked(name);
        }
        //从SharedPreferences获取数据:显示麦克风权限状态
        preferences = getSharedPreferences("voice", Context.MODE_PRIVATE);
        if (preferences != null) {
            boolean name = preferences.getBoolean("flag", falg);
            voiceSwitch.setChecked(name);
        }
        //从SharedPreferences获取数据:显示设备信息权限状态
        preferences = getSharedPreferences("device", Context.MODE_PRIVATE);
        if (preferences != null) {
            boolean name = preferences.getBoolean("flag", falg);
            deviceSwitch.setChecked(name);
        }
        //从SharedPreferences获取数据:显示相册权限状态
        preferences = getSharedPreferences("album", Context.MODE_PRIVATE);
        if (preferences != null) {
            boolean name = preferences.getBoolean("flag", falg);
            albumSwitch.setChecked(name);
        }
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
                PermissionsUtils.getInstance().chekPermissions(this, permissionsLocal, permissionsResult);
                    //将数据保存至SharedPreferences:
                    SharedPreferences preferences = getSharedPreferences("local", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("flag", true);
                    editor.commit();
                }else {
                    Toast.makeText(PermissionSettingActivity.this, "请滑动按钮申请权限!", Toast.LENGTH_SHORT).show();
                    //将数据保存至SharedPreferences:
                    SharedPreferences preferences = getSharedPreferences("local", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("flag", false);
                    editor.commit();
                }
                break;
            case R.id.switch_permission_canlder:
                if (calendarSwitch.isChecked()) {
                    Log.d(TAG, "onCheckedChanged: " + "日历权限" +permissionsCalendar);
                    PermissionsUtils.getInstance().chekPermissions(this, permissionsCalendar, permissionsResult);
                    //将数据保存至SharedPreferences:
                    SharedPreferences preferences = getSharedPreferences("calendar", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("flag", true);
                    editor.commit();
                }else {
                    Toast.makeText(PermissionSettingActivity.this, "请滑动按钮申请权限!", Toast.LENGTH_SHORT).show();
                    //将数据保存至SharedPreferences:
                    SharedPreferences preferences = getSharedPreferences("calendar", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("flag", false);
                    editor.commit();
                }
                break;
            case R.id.switch_permission_camera:
                if (cameraSwitch.isChecked()) {
                    Log.d(TAG, "onCheckedChanged: " + "日历权限" +permissionsCamera);
                    PermissionsUtils.getInstance().chekPermissions(this, permissionsCamera, permissionsResult);
                    //将数据保存至SharedPreferences:
                    SharedPreferences preferences = getSharedPreferences("camera", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("flag", true);
                    editor.commit();
                }else {
                    Toast.makeText(PermissionSettingActivity.this, "请滑动按钮申请权限!", Toast.LENGTH_SHORT).show();
                    //将数据保存至SharedPreferences:
                    SharedPreferences preferences = getSharedPreferences("camera", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("flag", false);
                    editor.commit();
                }
                break;
            case R.id.switch_permission_voice:
                if (voiceSwitch.isChecked()) {
                    Log.d(TAG, "onCheckedChanged: " + "日历权限" +permissionsVoice);
                    PermissionsUtils.getInstance().chekPermissions(this, permissionsVoice, permissionsResult);
                    //将数据保存至SharedPreferences:
                    SharedPreferences preferences = getSharedPreferences("voice", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("flag", true);
                    editor.commit();
                }else {
                    Toast.makeText(PermissionSettingActivity.this, "请滑动按钮申请权限!", Toast.LENGTH_SHORT).show();
                    //将数据保存至SharedPreferences:
                    SharedPreferences preferences = getSharedPreferences("voice", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("flag", false);
                    editor.commit();
                }
                break;
            case R.id.switch_permission_device:
                if (deviceSwitch.isChecked()) {
                    Log.d(TAG, "onCheckedChanged: " + "日历权限" +permissionsDevice);
                    PermissionsUtils.getInstance().chekPermissions(this, permissionsDevice, permissionsResult);
                    //将数据保存至SharedPreferences:
                    SharedPreferences preferences = getSharedPreferences("device", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("flag", true);
                    editor.commit();
                }else {
                    Toast.makeText(PermissionSettingActivity.this, "请滑动按钮申请权限!", Toast.LENGTH_SHORT).show();
                    //将数据保存至SharedPreferences:
                    SharedPreferences preferences = getSharedPreferences("device", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("flag", false);
                    editor.commit();
                }
                break;
            case R.id.switch_permission_album:
                if (albumSwitch.isChecked()) {
                    Log.d(TAG, "onCheckedChanged: " + "日历权限" +permissionsCamera);
                    PermissionsUtils.getInstance().chekPermissions(this, permissionsCamera, permissionsResult);
                    //将数据保存至SharedPreferences:
                    SharedPreferences preferences = getSharedPreferences("album", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("flag", true);
                    editor.commit();
                }else {
                    Toast.makeText(PermissionSettingActivity.this, "请滑动按钮申请权限!", Toast.LENGTH_SHORT).show();
                    //将数据保存至SharedPreferences:
                    SharedPreferences preferences = getSharedPreferences("album", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("flag", false);
                    editor.commit();
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
