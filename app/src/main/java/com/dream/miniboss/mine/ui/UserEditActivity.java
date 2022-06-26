package com.dream.miniboss.mine.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;
import com.dream.miniboss.databinding.ActivityUserEditBinding;
import com.example.liangmutian.mypicker.DataPickerDialog;
import com.example.liangmutian.mypicker.DatePickerDialog;
import com.example.liangmutian.mypicker.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建日期：2022-06-24 on 1:05
 * 描述:衣带渐宽终不悔、为伊消得人憔悴
 * 作者:HeGuiCun Administrator
 */
public class UserEditActivity extends BaseActivity implements View.OnClickListener {

    private Dialog dateDialog, chooseDialog;
    private List<String> list = new ArrayList<>();
    private ActivityUserEditBinding mBinding;
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
        mBinding.ivBack.setOnClickListener(this);
        mBinding.scviewIv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                if (mBinding.etName.isFocusable()){
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    mBinding.etName.setFocusable(false);
                    mBinding.etName.setFocusableInTouchMode(false);
                }else if (mBinding.etEmil.isFocusable()){
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
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.icon_user:
                //这里上传头像
                break;
            case R.id.et_name:
//                Log.i(TAG, "onViewClicked: 这是名字"+mEtName);
                mBinding.etName.setFocusable(true);
                mBinding.etName.setFocusableInTouchMode(true);

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
                break;

        }
    }
}
