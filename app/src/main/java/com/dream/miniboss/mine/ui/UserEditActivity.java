package com.dream.miniboss.mine.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;
import com.dream.miniboss.databinding.ActivityUserEditBinding;
import com.dream.miniboss.utils.MyDialog;
import com.dream.miniboss.utils.UploadUtil;
import com.example.liangmutian.mypicker.DataPickerDialog;
import com.example.liangmutian.mypicker.DatePickerDialog;
import com.example.liangmutian.mypicker.DateUtil;
import com.ruffian.library.widget.RImageView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 创建日期：2022-06-24 on 1:05
 * 描述:衣带渐宽终不悔、为伊消得人憔悴
 * 作者:HeGuiCun Administrator
 */
public class UserEditActivity extends BaseActivity implements View.OnClickListener{

    private Dialog dateDialog, chooseDialog;
    private List<String> list = new ArrayList<>();
    private ActivityUserEditBinding mBinding;
    private SharedPreferences mPreferences;

    @Override
    protected int initLayout() {
        return R.layout.activity_user_edit;
    }

    @Override
    protected void initView() {
        mBinding = ActivityUserEditBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        //自定义数据里面循环加入数据
        String[] data = getResources().getStringArray(R.array.list);
        for (String str : data) {
            list.add(str);
        }
        //取出存储数据显示在界面上
        mPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        mBinding.etName.setText(mPreferences.getString("et_name", ""));
        mBinding.etGender.setText(mPreferences.getString("et_gender", ""));
        mBinding.etBirthTime.setText(mPreferences.getString("et_birth_time", ""));
        mBinding.etEmil.setText(mPreferences.getString("et_emil", ""));

    }

    @Override
    protected void initData() {
        onEvent();
    }

    private void onEvent() {
        mBinding.iconUser.setOnClickListener(this);
        mBinding.etName.setOnClickListener(this);
        mBinding.etGender.setOnClickListener(this);
        mBinding.etBirthTime.setOnClickListener(this);
        mBinding.etEmil.setOnClickListener(this);
        mBinding.buttonPull.setOnClickListener(this);
        //返回按钮的监听事件
        mBinding.titleBar.getLeftView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mBinding.scviewIv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (mBinding.etName.isFocusable()) {
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    mBinding.etName.setFocusable(false);
                    mBinding.etName.setFocusableInTouchMode(false);
                } else if (mBinding.etEmil.isFocusable()) {
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    mBinding.etEmil.setFocusable(false);
                    mBinding.etEmil.setFocusableInTouchMode(false);
                }
                return false;
            }
        });
    }

    /**
     * 设置性别选择
     * chooseDialog
     */
    private void showChooseDialog(List<String> mlist) {
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(1).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
//                        mTextView.setText(itemValue);
                        //设置数据源
                        mBinding.etGender.setText(itemValue);

                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

    /**
     * 设置出生日期
     *
     * @param date
     */
    private void showDateDialog(List<Integer> date) {
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(this);
        builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
                    @Override
                    public void onDateSelected(int[] dates) {

                        mBinding.etBirthTime.setText(dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                                + (dates[2] > 9 ? dates[2] : ("0" + dates[2])));

                    }

                    @Override
                    public void onCancel() {
                        ToastUtils.showShort("请选择你的出生年月");
                    }
                })

                .setSelectYear(date.get(0) - 1)
                .setSelectMonth(date.get(1) - 1)
                .setSelectDay(date.get(2) - 1);

        builder.setMaxYear(DateUtil.getYear());
        builder.setMaxMonth(DateUtil.getDateForString(DateUtil.getToday()).get(1));
        builder.setMaxDay(DateUtil.getDateForString(DateUtil.getToday()).get(2));
        dateDialog = builder.create();
        dateDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_user:
                //这里上传头
                MyDialog myDialog = new MyDialog(this, MyDialog.PICK_AVATAR);
                myDialog.show();
                myDialog.mSelectPicture.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                myDialog.mSelectCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i(TAG, "onClick: " + "点击拍照上传");

                    }
                });
                break;
            case R.id.et_name:
//                Log.i(TAG, "onViewClicked: 这是名字"+mEtName);
                mBinding.etName.setFocusable(true);
                mBinding.etName.setFocusableInTouchMode(true);

                mBinding.etName.requestFocus();
                mBinding.etName.setSelection(mBinding.etName.getText().length());

                break;
            case R.id.et_gender:
                showChooseDialog(list);

                break;
            case R.id.et_birth_time:
                showDateDialog(DateUtil.getDateForString("1990-01-01"));

                break;
            case R.id.et_emil:
                mBinding.etEmil.setFocusable(true);
                mBinding.etEmil.setFocusableInTouchMode(true);
                mBinding.etEmil.requestFocus();
                //临时存储数据
                mBinding.etEmil.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                mBinding.etEmil.setSelection(mBinding.etEmil.getText().length());
                break;
            case R.id.buttonPull:
                //临时存储,名字数据

                SharedPreferences.Editor etNameEditor = mPreferences.edit();
                etNameEditor.putString("et_name", mBinding.etName.getText().toString());
                etNameEditor.commit();
                //临时存储,性别数据
                SharedPreferences.Editor genderEditor = mPreferences.edit();
                genderEditor.putString("et_gender", mBinding.etGender.getText().toString());
                genderEditor.commit();
                //临时存储出生年月数据
                SharedPreferences.Editor birthEditor = mPreferences.edit();
                birthEditor.putString("et_birth_time", mBinding.etBirthTime.getText().toString());
                birthEditor.commit();

                //验证邮箱格式
                if (isEmailValid(mBinding.etEmil.getText().toString())) {
                    //临时存储邮箱数据
                    SharedPreferences.Editor emailEditor = mPreferences.edit();
                    emailEditor.putString("et_emil", mBinding.etEmil.getText().toString());
                    emailEditor.commit();
                    onBackPressed();
                } else {
                    ToastUtils.showShort("请输入正确的邮箱格式");
                }


                break;

            default:

        }
    }

    /**
     * 验证邮箱格式
     *
     * @param email
     * @return
     */
    public boolean isEmailValid(String email) {

        String regExpn =

                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"

                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"

                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."

                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"

                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"

                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())

            return true;

        else

            return false;

    }

}
