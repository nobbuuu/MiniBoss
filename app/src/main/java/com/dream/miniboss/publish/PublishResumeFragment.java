package com.dream.miniboss.publish;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.ColorInt;

import com.blankj.utilcode.util.ToastUtils;
import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseFragment;
import com.hjq.bar.TitleBar;
import com.ruffian.library.widget.RRadioButton;
import com.ruffian.library.widget.RTextView;

/**
 * 创建日期：2022-07-04 on 0:12
 * 描述:衣带渐宽终不悔、为伊消得人憔悴
 * 作者:HeGuiCun Administrator
 */
public class PublishResumeFragment extends BaseFragment {
    private static final String TAG = "TAG";
    RadioGroup skillSelectGroup, workTimeSelectGroupOne,workTimeSelectGroupTwo,educationSelectOne,educationSelectTwo;
    RTextView publishResume;
    TitleBar mTitleBar;

    @Override
    protected int setLayout() {
        return R.layout.fragment_publish;
    }

    @Override
    protected void initView() {
        mTitleBar = fvbyid(R.id.title_Publish);
        skillSelectGroup = fvbyid(R.id.radio_group_skill);
        workTimeSelectGroupOne=fvbyid(R.id.work_time_group_one);
        workTimeSelectGroupTwo=fvbyid(R.id.work_time_group_two);
        educationSelectOne=fvbyid(R.id.education_group_one);
        educationSelectTwo=fvbyid(R.id.education_group_two);
        publishResume = fvbyid(R.id.textView_publish);
    }

    @Override
    protected void initData() {

        mTitleBar.getLeftView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        event();

    }

    private void event() {
        skillSelectGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i(TAG, "onCheckedChanged: "+checkedId);
                for (int i = 0; i < 4; i++) {
                    RadioButton mRadioButton = (RadioButton) skillSelectGroup.getChildAt(i);
                    if (mRadioButton.isChecked()) {
                        ToastUtils.showShort(mRadioButton.getText().toString());
                    }
                }
            }
        });
        workTimeSelectGroupOne.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < 4; i++) {
                    RadioButton mRadioButton = (RadioButton) workTimeSelectGroupOne.getChildAt(i);
                    if (mRadioButton.isChecked()) {

                        ToastUtils.showShort(mRadioButton.getText().toString());
                       workTimeSelectGroupTwo.clearCheck();
                    }
                }
            }
        });

        workTimeSelectGroupTwo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < 2; i++) {
                    RadioButton mRadioButton = (RadioButton) workTimeSelectGroupTwo.getChildAt(i);
                    if (mRadioButton.isChecked()) {

                        ToastUtils.showShort(mRadioButton.getText().toString());
                       workTimeSelectGroupOne.clearCheck();
                    }
                }
            }
        });
        educationSelectOne.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i <4; i++) {
                    RadioButton mRadioButton = (RadioButton) educationSelectOne.getChildAt(i);
                    if (mRadioButton.isChecked()) {

                        ToastUtils.showShort(mRadioButton.getText().toString());
                        educationSelectTwo.clearCheck();
                    }
                }
            }
        });

        educationSelectTwo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i <4; i++) {
                    RadioButton mRadioButton = (RadioButton) educationSelectTwo.getChildAt(i);
                    if (mRadioButton.isChecked()) {

                        ToastUtils.showShort(mRadioButton.getText().toString());
                        educationSelectOne.clearCheck();
                    }
                }
            }
        });
        publishResume.setOnClickListener(this);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.textView_publish:
                startActivity(new Intent(getContext(), PublishResumeActivity.class));
                break;
            default:

        }
    }

//    @Override
//    public void onCheckedChanged(RadioGroup group, int checkedId) {
//        switch (group.getId()){
//
//            case R.id.radio_group_skill:
//                Log.i(TAG, "onCheckedChanged: "+group.getId());
//                RadioButton mRadioButton = (RadioButton) group.getChildAt(0);
//                if (mRadioButton.isChecked()){
//                    ToastUtils.showShort(mRadioButton.getText().toString());
//                }
//                break;
//        }
////        for (int i = 0; i < 4; i++) {
////            RadioButton mRadioButton = (RadioButton) skillSelectGroup.getChildAt(i);
////            if (mRadioButton.isChecked()) {
////
////                break;
////
////            }
////        }
//    }
}
