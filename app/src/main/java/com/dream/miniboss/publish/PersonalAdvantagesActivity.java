package com.dream.miniboss.publish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;
import com.hjq.bar.TitleBar;
import com.ruffian.library.widget.REditText;
import com.ruffian.library.widget.RTextView;

public class PersonalAdvantagesActivity extends BaseActivity {
    CharSequence temp;
    REditText mPersonEdittext;
    RTextView mNumTextView;
    TitleBar mTitleBar;
    Button mCommitButton;
    SharedPreferences mPreferences;
    @Override
    protected int initLayout() {
        return R.layout.activity_personal_advantages;
    }

    @Override
    protected void initView() {
        mTitleBar=fvbi(R.id.title_bar);
        mPersonEdittext=fvbi(R.id.et_person_advantage);
        mNumTextView=fvbi(R.id.tv_num);
        mCommitButton=fvbi(R.id.person_commit_btn);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPreferences = getSharedPreferences("PersonData", Context.MODE_PRIVATE);
        mPersonEdittext.setText(mPreferences.getString("et_text", ""));
        mNumTextView.setText(String.valueOf(mPreferences.getInt("et_length",0)));
    }

    @Override
    protected void initData() {
        event();

    }

    private void event() {
        mTitleBar.getLeftView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //设置edit的监听
        mPersonEdittext.addTextChangedListener(new TextWatcher() {

            int startEdit;
             int endEdit;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
               temp=s;

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                   startEdit=mPersonEdittext.getSelectionStart();
                   endEdit=mPersonEdittext.getSelectionEnd();
                   mNumTextView.setText(String.valueOf(temp.length()));
                   if (temp.length()>300){
                       s.delete(startEdit-1,endEdit);
                       int tempSelection=startEdit;
                       mPersonEdittext.setText(s);
                       mPersonEdittext.setSelection(tempSelection);
                       ToastUtils.showShort("你输入的字已经超过了！");

                   }

            }
        });
        //提交按钮
        mCommitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor etNameEditor = mPreferences.edit();
                etNameEditor.putString("et_text", mPersonEdittext.getText().toString());
                etNameEditor.putInt("et_length",temp.length());
                etNameEditor.commit();
                Log.i(TAG, "onClick: "+temp.length());
                onBackPressed();
            }
        });
    }
}