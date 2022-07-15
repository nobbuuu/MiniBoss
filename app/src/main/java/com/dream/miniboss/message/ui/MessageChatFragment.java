package com.dream.miniboss.message.ui;

import android.view.View;

import com.dream.miniboss.MiniBossAppKt;
import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseFragment;
import com.hjq.bar.TitleBar;

public class MessageChatFragment  extends BaseFragment {
    TitleBar mTitleBar;

    @Override
    protected int setLayout() {
        return R.layout.fragment_chat_message;
    }

    @Override
    protected void initView() {
        mTitleBar=fvbyid(R.id.title_bar_message);
    }

    @Override
    protected void initData() {
    event();
    }

    private void event() {
        mTitleBar.getLeftView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
