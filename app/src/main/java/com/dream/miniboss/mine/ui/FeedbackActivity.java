package com.dream.miniboss.mine.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;
import com.hjq.bar.TitleBar;
import com.ruffian.library.widget.RRadioButton;
import com.ruffian.library.widget.RTextView;

public class FeedbackActivity extends BaseActivity {
    TitleBar mTitleBar;
    RRadioButton mTextViewSoftNoUse;
    EditText feedEdittext;
    RadioGroup mRadioGroup;
    boolean flag = false;

    @Override
    protected int initLayout() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initView() {
        mTitleBar = fvbi(R.id.title_feed_back);
        mTextViewSoftNoUse = fvbi(R.id.rtv_soft_notUse);
        feedEdittext = fvbi(R.id.edittext_feed_back);
        mRadioGroup = fvbi(R.id.feed_back_radio_group);
    }

    @Override
    protected void initData() {
        feedEdittext.setSelection(feedEdittext.getText().length());
        event();
    }

    private void event() {
        //点击标题栏的返回按钮
        mTitleBar.getLeftView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        //点击RTextview
//     mTextViewSoftNoUse.setOnClickListener(new View.OnClickListener() {
//         @Override
//         public void onClick(View v) {
//            if (flag==false){
//                Log.i(TAG, "onClick: "+mTextViewSoftNoUse.getText());
//               //mTextViewSoftNoUse.getHelper().getIconPressedRight().setVisible(false,false);
//                mTextViewSoftNoUse.getHelper().getIconPressedRight().setVisible(true,false);
//                flag=true;
//            }else {
//                Log.i(TAG, "onClick: "+mTextViewSoftNoUse.getHelper().getIconPressedRight());
//                mTextViewSoftNoUse.getHelper().getIconPressedRight().setVisible(false,false);
//                flag=false;
//            }
//         }
//     });
        //设置radioGroup的监听
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//             if (mTextViewSoftNoUse.isChecked()){
//                 ToastUtils.showShort(mTextViewSoftNoUse.getText());
//             }

                //获取选项的文案
                for (int i = 0; i < 5; i++) {
                    RadioButton mRadioButton = (RadioButton) mRadioGroup.getChildAt(i);
                    if (mRadioButton.isChecked()) {

                        ToastUtils.showShort(mRadioButton.getText().toString());
//                        workTimeSelectGroupOne.clearCheck();
                    }
                }

            }
        });

    }
}