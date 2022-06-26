package com.dream.miniboss.mine.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static android.content.ContentValues.TAG;

import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseFragment;
import com.dream.miniboss.main.MainActivity;

/**
 * 创建日期：2022-06-21 on 0:58
 * 描述:我的页面
 * 作者:HeGuiCun Administrator
 */
public class MyFragment extends BaseFragment {

    ImageView mImageEdit;
    ImageView onLineChat;
    TextView mPhoneNumber;
    LinearLayout mLinearLayout;

    @Override
    protected int setLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {
        mImageEdit = fvbyid(R.id.image_edit);
        onLineChat = fvbyid(R.id.iv_online_chat);
        mPhoneNumber=fvbyid(R.id.tv_phone_number);
        mLinearLayout=fvbyid(R.id.lv_qianhuan_zhaopin);
    }

    @Override
    protected void initData() {
        mImageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: " + "测试跳转方法");
                Intent mIntent = new Intent();
                mIntent.setClass(getContext(), UserEditActivity.class);
                startActivity(mIntent);
            }
        });
        //设置打电话
        onLineChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent mIntent=new Intent(Intent.ACTION_CALL);
//                Uri mdata= Uri.parse("tel:"+mPhoneNumber.getText());
//                mIntent.setData(mdata);
//                Log.i(TAG, "onClick: "+mPhoneNumber.getText());
//                getActivity().startActivity(mIntent);
                request_permission();
            }
        });
        //设置切换招聘界面
        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent();
                mIntent.setClass(getContext(), MainActivity.class);
                startActivity(mIntent);
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
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CALL_PHONE},10010);
            Log.d(TAG, "request_permission():正在申请权限！");
        }else {
            Log.d(TAG, "request_permission():已经拥有权限！");
            callUp();
        }
    }

    /**
     * 请求权限结果的回调
     * @param requestCode   传入的请求代码
     * @param permissions   请求的权限
     * @param grantResults  相应权限授予的结果，可以是PERMISSION_GRANTED，或DENIED.从不为空
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
                }else {
                    Log.d(TAG, "onRequestPermissionsResult(): 权限已拒绝！");
                }
                break;
        }
    }

    private void callUp() {
        Intent intent = new Intent();
        // 设置要执行的一般操作
        // action：操作名称。特定与应用程序的操作应以供应商的包名为前缀
        intent.setAction(Intent.ACTION_CALL);
        // 设置意图正在操作的数据
        // tel:是必须要添加的参数，后面拼接的字符串为电话号
        intent.setData(Uri.parse("tel:" + mPhoneNumber.getText()));
        startActivity(intent);

    }
}
