package com.dream.miniboss.message.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;
import com.hjq.bar.TitleBar;

public class MessageChatDetailActivity extends BaseActivity {
     TitleBar chatMessageTitle;
    @Override
    protected int initLayout() {
        return R.layout.activity_message_chat_detail;
    }

    @Override
    protected void initView() {
        chatMessageTitle=fvbi(R.id.chat_message_title);
    }

    @Override
    protected void initData() {
       event();
    }

    private void event() {
        chatMessageTitle.getLeftView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}