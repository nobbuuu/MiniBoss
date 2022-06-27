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

/**
 * 创建日期：2022-06-27 on 1:42
 * 描述:衣带渐宽终不悔、为伊消得人憔悴
 * 作者:HeGuiCun Administrator
 */
public class PermissionSettingActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {
    TitleBar mTitleBar;
    Switch localSwitch, calendarSwitch, cameraSwitch, voiceSwitch, deviceSwitch, albumSwitch;
    String[] permissions;

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
         permissions = new String[]{Manifest.permission.LOCATION_HARDWARE, Manifest.permission.CAMERA, Manifest.permission.ADD_VOICEMAIL,Manifest.permission.READ_CALENDAR,Manifest.permission.WRITE_CALENDAR,Manifest.permission.READ_PHONE_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
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
                Log.d(TAG, "onCheckedChanged: "+"这是位置权限");
                PermissionsUtils.getInstance().chekPermissions(this, permissions= new String[0], permissionsResult);
                break;
            case R.id.switch_permission_canlder:

                break;
            case R.id.switch_permission_camera:

                break;
            case R.id.switch_permission_voice:

                break;
            case R.id.switch_permission_device:

                break;
            case R.id.switch_permission_album:

                break;
            default:
        }

    }
    //创建监听权限的接口对象
    PermissionsUtils.IPermissionsResult permissionsResult = new PermissionsUtils.IPermissionsResult() {
        @Override
        public void passPermissons() {
            Toast.makeText(PermissionSettingActivity.this, "权限通过，可以做其他事情!", Toast.LENGTH_SHORT).show();
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
