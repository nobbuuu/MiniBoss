package com.dream.miniboss.mine.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;


import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationManagerCompat;

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
    Switch mRemindSwitch;
    boolean isEnabled;

    @Override
    protected int initLayout() {
        return R.layout.actiivity_setting_system;
    }

    @Override
    protected void initView() {
        mTitleBar = fvbi(R.id.system_setting_titleBar);
        mLayoutPermission = fvbi(R.id.lv_permission);
        mRemindSwitch = fvbi(R.id.switch_remind_message);
        //设置通知消息提醒时候的状态
        NotificationManagerCompat notification = NotificationManagerCompat.from(this);
        isEnabled = notification.areNotificationsEnabled();
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
        event();
    }

    /**
     * 设置打开消息提醒
     */
    private void event() {

        mRemindSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG, "onCheckedChanged: "+"-----------");
                if (!isEnabled) {
                    Log.d(TAG, "onCheckedChanged: "+"打开了权限");
                    //未打开通知
                    AlertDialog alertDialog = new AlertDialog.Builder(SystemSettingActivity.this)
                            .setTitle("提示")
                            .setMessage("请在“通知”中打开通知权限")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    Intent intent = new Intent();
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                        intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                                        intent.putExtra("android.provider.extra.APP_PACKAGE", SystemSettingActivity.this.getPackageName());
                                    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {  //5.0
                                        intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                                        intent.putExtra("app_package", SystemSettingActivity.this.getPackageName());
                                        intent.putExtra("app_uid", SystemSettingActivity.this.getApplicationInfo().uid);
                                        startActivity(intent);
                                    } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {  //4.4
                                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        intent.addCategory(Intent.CATEGORY_DEFAULT);
                                        intent.setData(Uri.parse("package:" + SystemSettingActivity.this.getPackageName()));
                                    } else if (Build.VERSION.SDK_INT >= 15) {
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                        intent.setData(Uri.fromParts("package", SystemSettingActivity.this.getPackageName(), null));
                                    }
                                    startActivity(intent);

                                }
                            })
                            .create();
                    alertDialog.show();
                    alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                }else {
                    Log.d(TAG, "onCheckedChanged: "+"这是已经打开通知消息了");
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lv_permission:
                Log.d(TAG, "onClick: " + "---------");
//                toClass(this,PermissionSettingActivity.class);
                Intent mIntent = new Intent();
                mIntent.setClass(this, PermissionSettingActivity.class);
                startActivity(mIntent);
                break;


        }
    }
}
