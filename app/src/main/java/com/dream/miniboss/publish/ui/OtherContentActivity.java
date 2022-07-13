package com.dream.miniboss.publish.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;
import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;
import com.hjq.bar.TitleBar;
import com.ruffian.library.widget.REditText;
import com.ruffian.library.widget.RTextView;

public class OtherContentActivity extends BaseActivity {

    REditText mOtherEdittext;
    RTextView mNumTextView;
    TitleBar mTitleBar;
    Button mCommitButton;
    SharedPreferences mPreferences;

    @Override
    protected int initLayout() {
        return R.layout.activity_other_content;
    }

    @Override
    protected void initView() {
        mTitleBar = fvbi(R.id.title_bar);
        mOtherEdittext = fvbi(R.id.et_other_content);
        mNumTextView = fvbi(R.id.tv_num);
        mCommitButton = fvbi(R.id.other_content_btn);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPreferences = getSharedPreferences("OtherData", Context.MODE_PRIVATE);

        mOtherEdittext.setText(mPreferences.getString("et_content", ""));


        mNumTextView.setText(String.valueOf(mPreferences.getInt("et_content_length", 0)));

        Log.i(TAG, "onResume: " + mPreferences.getInt("et_content_length", 0));
    }

    @Override
    protected void initData() {
        event();
    }

    private void event() {
        //标题栏返回按钮
        mTitleBar.getLeftView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //设置edit的监听
        mOtherEdittext.addTextChangedListener(new TextWatcher() {
            CharSequence temp;
            int startEdit;
            int endEdit;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                startEdit = mOtherEdittext.getSelectionStart();
                endEdit = mOtherEdittext.getSelectionEnd();
                mNumTextView.setText(String.valueOf(temp.length()));
                if (temp.length() > 300) {
                    s.delete(startEdit - 1, endEdit);
                    int tempSelection = startEdit;
                    mOtherEdittext.setText(s);
                    mOtherEdittext.setSelection(tempSelection);
                    ToastUtils.showShort("你输入的字已经超过了！");
                }
                SharedPreferences.Editor etNameEditor = mPreferences.edit();
                etNameEditor.putString("et_content", mOtherEdittext.getText().toString());
                etNameEditor.putInt("et_content_length", temp.length());
                etNameEditor.clear();
                etNameEditor.commit();

            }
        });
        //提交按钮
        mCommitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                onBackPressed();
            }
        });

    }
}