package com.dream.miniboss.mine.ui;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.navigation.Navigation;


import static android.content.ContentValues.TAG;

import com.blankj.utilcode.util.ToastUtils;

import com.dream.miniboss.MiniBossAppKt;
import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseFragment;
import com.dream.miniboss.login.LoginCodeActivity;
import com.dream.miniboss.main.MainActivity;
import com.dream.miniboss.utils.LoginUIHelper;
import com.ruffian.library.widget.RImageView;
import com.ruffian.library.widget.RTextView;
import com.tcl.tclzjpro.main.MainManagerKt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import cn.jiguang.verifysdk.api.AuthPageEventListener;
import cn.jiguang.verifysdk.api.JVerificationInterface;
import cn.jiguang.verifysdk.api.JVerifyUIClickCallback;
import cn.jiguang.verifysdk.api.JVerifyUIConfig;
import cn.jiguang.verifysdk.api.LoginSettings;
import cn.jiguang.verifysdk.api.PrivacyBean;
import cn.jiguang.verifysdk.api.VerifyListener;

/**
 * 创建日期：2022-06-21 on 0:58
 * 描述:我的页面
 * 作者:HeGuiCun Administrator
 */
public class MyFragment extends BaseFragment {
    RImageView userIcon;
    ImageView mImageEdit;
    ImageView onLineChat;
    TextView mPhoneNumber, userName, desc;
    RTextView changeNameTv;
    boolean temp=false;

    LinearLayout mLinearLayout, mSystemSetting, mAuthyName, recruitNameLayout, publishManageLayout,
            resumeLayout, needPeopleLayput,aboutUsLayout,meritRecordLayout;
    Uri imageUri;
    Bitmap bitmap = null;
    private SharedPreferences mPreferences;

    @Override
    protected int setLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {
        mSystemSetting = fvbyid(R.id.lv_setting_system);
        mImageEdit = fvbyid(R.id.image_edit);
        onLineChat = fvbyid(R.id.iv_online_chat);
        mPhoneNumber = fvbyid(R.id.tv_phone_number);
        mLinearLayout = fvbyid(R.id.lv_qianhuan_zhaopin);
        mAuthyName = fvbyid(R.id.lv_authy_name);
        userIcon = fvbyid(R.id.user_icon);
        userName = fvbyid(R.id.user_name);
        desc = fvbyid(R.id.tv_company);
        //报名 发布管理
        recruitNameLayout = fvbyid(R.id.name_recruit_ll);
        publishManageLayout = fvbyid(R.id.mange_publish_ll);
        aboutUsLayout=fvbyid(R.id.about_us_ll);
        meritRecordLayout=fvbyid(R.id.merit_record_ll);
        //这是收简历，招聘 管理
        changeNameTv=fvbyid(R.id.name_change_tv);
        resumeLayout = fvbyid(R.id.resume_recruit_ll);
        needPeopleLayput = fvbyid(R.id.need_people_ll);

    }

    @Override
    public void onResume() {
        super.onResume();
        //将存储的图片显示在头像上面

        try {
            File outputImage = new File(getContext().getExternalCacheDir(), "user_images.jpg");
            imageUri = FileProvider.getUriForFile(getContext(), "com.dream.miniboss.FileProvider", outputImage);
            bitmap = BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(imageUri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (bitmap == null) {
            userIcon.setImageResource(R.mipmap.usericon_grey);
            userName.setText("未注册/登录");
            desc.setText("点击头像可登录/注册");
        } else {

            userIcon.setImageBitmap(bitmap);
            //将文件里面的名字也存储在上面
            //取出存储数据显示在界面上
            mPreferences = getContext().getSharedPreferences("UserData", Context.MODE_PRIVATE);
            userName.setText(mPreferences.getString("et_name", ""));
            desc.setText(mPreferences.getString("et_desc", ""));
        }
    }

    @Override
    protected void initData() {
        mImageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent();
                mIntent.setClass(getContext(), UserEditActivity.class);
                startActivity(mIntent);
            }
        });
        //发布管理界面
        publishManageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v)
                        .navigate(R.id.myFragment_to_navigation_square);
            }
        });

         //关于我们界面
        aboutUsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MiniBossAppKt.getMApplication(),AboutUsActivity.class));
            }
        });
        //设置打电话
        mPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request_permission();
            }
        });
        //设置切换招聘界面
        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temp==false){
                    changeNameTv.setText("切换成应聘端");
                    recruitNameLayout.setVisibility(View.GONE);
                    publishManageLayout.setVisibility(View.GONE);
                    resumeLayout.setVisibility(View.VISIBLE);
                    needPeopleLayput.setVisibility(View.VISIBLE);
                    temp=true;
                }else {
                    changeNameTv.setText("切换成招聘端");
                    recruitNameLayout.setVisibility(View.VISIBLE);
                    publishManageLayout.setVisibility(View.VISIBLE);
                    resumeLayout.setVisibility(View.GONE);
                    needPeopleLayput.setVisibility(View.GONE);
                    temp=false;
                }

//                Intent mIntent = new Intent();
//                mIntent.setClass(getContext(), MainActivity.class);
//                startActivity(mIntent);
            }
        });

        mSystemSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent();
                mIntent.setClass(getContext(), SystemSettingActivity.class);
                startActivity(mIntent);
            }
        });
        //设置到聊天界面
        onLineChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: " + "----------");
//                Intent mIntent=new Intent();
//                mIntent.setClass(v.getContext(), MessageFragment.class);
//                v.getContext().startActivity(mIntent);
            }
        });
        //点击头像进入手机号码一键登录
        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //自定义界面
                TextView mRTextView = new TextView(getContext());
                mRTextView.setText("其他手机号码登录");
                RelativeLayout.LayoutParams mLayoutParams1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                mLayoutParams1.setMargins(LoginUIHelper.dpToPx(130.0f), LoginUIHelper.dpToPx(445.0f), 0, 0);
                mRTextView.setLayoutParams(mLayoutParams1);
                //增加一个自定义导航栏"切换到招聘端"
                TextView logView = new TextView(getContext());
                logView.setText("切换到招聘端");
                logView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                logView.setTypeface(Typeface.DEFAULT_BOLD);
                RelativeLayout.LayoutParams logViewParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                logViewParam.setMargins(LoginUIHelper.dpToPx(226.0f), LoginUIHelper.dpToPx(24.0f), 0, 0);
                logView.setLayoutParams(logViewParam);
                //增加切换图标
                ImageView goImageView = new ImageView(getContext());
                goImageView.setImageResource(R.mipmap.goin);
                RelativeLayout.LayoutParams goImageviewParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                goImageviewParam.setMargins(LoginUIHelper.dpToPx(327.0f), LoginUIHelper.dpToPx(30.0f), 0, 0);
                goImageView.setLayoutParams(goImageviewParam);
                //增加"欢迎使用"
                TextView helloTextView = new TextView(getContext());
                helloTextView.setText("欢迎使用");
                helloTextView.setTypeface(Typeface.DEFAULT_BOLD);
                helloTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 32);
                RelativeLayout.LayoutParams helloParams1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                helloParams1.setMargins(LoginUIHelper.dpToPx(16.0f), LoginUIHelper.dpToPx(90.0f), 0, 0);
                helloTextView.setLayoutParams(helloParams1);
                //找工作一个旦光就够了
                TextView resumeTextView = new TextView(getContext());
                resumeTextView.setText("找工作用旦光工匠，一个就够了");
                resumeTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                RelativeLayout.LayoutParams resumeParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                resumeParams.setMargins(LoginUIHelper.dpToPx(16.0f), LoginUIHelper.dpToPx(143.0f), 0, 0);
                resumeTextView.setLayoutParams(resumeParams);
                //增加底部隐私条款
                List<PrivacyBean> list = new ArrayList<>();
                PrivacyBean privacyBean1 = new PrivacyBean("用户协议", "https://www.baidu.com", "、");
                PrivacyBean privacyBean2 = new PrivacyBean("隐私政策", "https://www.baidu.com", "、");
                list.add(privacyBean1);
                list.add(privacyBean2);
                JVerifyUIConfig jVerifyUIConfig = new JVerifyUIConfig.Builder()
                        .setPrivacyOffsetX(20)
                        .setPrivacyState(true)
                        .addCustomView(resumeTextView, true, new JVerifyUIClickCallback() {
                            @Override
                            public void onClicked(Context context, View view) {

                            }
                        })
                        .addCustomView(helloTextView, false, new JVerifyUIClickCallback() {
                            @Override
                            public void onClicked(Context context, View view) {

                            }
                        })
                        .addCustomView(goImageView, false, new JVerifyUIClickCallback() {
                            @Override
                            public void onClicked(Context context, View view) {

                            }
                        })
                        .addCustomView(logView, false, new JVerifyUIClickCallback() {
                            @Override
                            public void onClicked(Context context, View view) {

                            }
                        })
                        .addCustomView(mRTextView, true, new JVerifyUIClickCallback() {
                            @Override
                            public void onClicked(Context context, View view) {
                                startActivity(new Intent(getContext(), LoginCodeActivity.class));
                            }
                        })

                        .setPrivacyNameAndUrlBeanList(list)
                        .setLogBtnText("立即登录")
                        .setLogBtnHeight(48)
                        .setNumberTextBold(true)
                        .setNumberSize(24)
                        .setLogBtnImgPath("buttonbg")
                        .setNumFieldOffsetY(LoginUIHelper.dpToPx(110.0f))
                        .setSloganOffsetY(LoginUIHelper.dpToPx(122.0f))
                        .setLogBtnOffsetY(LoginUIHelper.dpToPx(130.0f))
                        .setLogoHidden(true)
                        .setNavHidden(true)
                        .enableHintToast(true, Toast.makeText(getActivity(), "请先同意页面底部的隐私条款", Toast.LENGTH_LONG))
                        .build();
                JVerificationInterface.setCustomUIWithConfig(jVerifyUIConfig);
                //配置登录文件
                LoginSettings settings = new LoginSettings();
                settings.setAutoFinish(true);//设置登录完成后是否自动关闭授权页
                settings.setTimeout(6 * 1000);//设置超时时间，单位毫秒。 合法范围（0，30000],范围以外默认设置为10000
                settings.setAuthPageEventListener(new AuthPageEventListener() {
                    @Override
                    public void onEvent(int cmd, String msg) {

                        //do something...
                    }
                });
                //尝试授权登陆
                JVerificationInterface.loginAuth(getActivity(), true, new VerifyListener() {
                    @Override
                    public void onResult(int code, String s, String s1) {
                        if (code == 6000) {
                            startActivity(new Intent(getContext(), UserEditActivity.class));
                            ToastUtils.showShort("登陆成功");
                        } else {
                            ToastUtils.showShort("当前无法登陆，已转其他方式登陆");
                            //startActivity(new Intent(getContext(),LoginCodeActivity.class));
                        }
                    }
                });
            }
        });
        //实名认证
        mAuthyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AuthNameActivity.class));
            }
        });
        //记功记账
        meritRecordLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),MeritRecordingActivity.class));
            }
        });

    }


    @Override
    public void onClick(View v) {

    }
    //动态添加拨打电话的权限

    /**
     * 动态请求权限
     */
    private void request_permission() {
        /*
        ContextCompat.checkSelfPermission:检查是否已被赋予特点权限
        PackageManager.PERMISSION_GRANTED:权限检查结果，如果权限已授予给定包，则由checkPermission()返回
        */
        // 如果CALL_PHONE权限没有被赋予
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // 请求权限
            // permissions请求的权限
            // requestCode:应用程序特定的请求代码以匹配报告给OnRequestPermissionsResultCallback#onRequestPermissionsResult(int, String[], int[])}
            // 也就是下面回调的OnRequestPermissionResult()方法
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 10010);
            Log.d(TAG, "request_permission():正在申请权限！");
        } else {
            Log.d(TAG, "request_permission():已经拥有权限！");
            callUp();
        }
    }

    /**
     * 请求权限结果的回调
     *
     * @param requestCode  传入的请求代码
     * @param permissions  请求的权限
     * @param grantResults 相应权限授予的结果，可以是PERMISSION_GRANTED，或DENIED.从不为空
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 10010:
                // 电话权限
                // 如果权限授予结果为PERMISSION_GRANTED,那么表示权限授予成功！
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callUp();
                    Log.d(TAG, "onRequestPermissionsResult(): 获取权限，可以拨打电话！");
                } else {
                    Log.d(TAG, "onRequestPermissionsResult(): 权限已拒绝！");
                }
                break;
        }
    }

    private void callUp() {
        Log.i(TAG, "callUp: " + mPhoneNumber.getText());
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setMessage(mPhoneNumber.getText())
                .setTitle("呼叫")

                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("确定拨打", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent intent = new Intent();
                        // 设置要执行的一般操作
                        // action：操作名称。特定与应用程序的操作应以供应商的包名为前缀
                        intent.setAction(Intent.ACTION_CALL);
                        // 设置意图正在操作的数据
                        // tel:是必须要添加的参数，后面拼接的字符串为电话号
                        intent.setData(Uri.parse("tel:" + mPhoneNumber.getText()));
                        startActivity(intent);

                    }
                }).create();

        alertDialog.show();
        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
        // 设置对话框的位置偏下
//        Window window = alertDialog.getWindow();
//        window.setBackgroundDrawable(null); // 重设background
//        WindowManager.LayoutParams wlp = window.getAttributes();
//        wlp.gravity = Gravity.BOTTOM;
////        WindowManager wm = (WindowManager) ContextCompat.getSystemService(getContext().WINDOW_SERVICE);
////        Display display = wm.getDefaultDisplay();
////        wlp.width = display.getWidth();
//        window.setAttributes(wlp);


    }
}
