package com.dream.miniboss.message.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.dream.miniboss.MiniBossAppKt;
import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseFragment;
import com.dream.miniboss.message.adapter.MessageChatAdapter;
import com.dream.miniboss.message.bean.MessageChatBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MessagePersonFragment extends BaseFragment {
    XRecyclerView mXRecyclerView;
    MessageChatAdapter mChatAdapter;
    List<MessageChatBean> mChatBeanList;

    public MessagePersonFragment() {


    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_message_person;
    }

    @Override
    protected void initView() {
        mXRecyclerView = fvbyid(R.id.message_chat_rv);
    }

    @Override
    protected void initData() {
        mChatBeanList = new ArrayList<>();
        for (int i = 0; i <2 ; i++) {
            mChatBeanList.add(new MessageChatBean(R.mipmap.messageicon,"何先生","旦光人才服务有限公司","3月21日","工资没有什么问题明天就来上班吧"));
        }
        mChatAdapter = new MessageChatAdapter(mChatBeanList, MiniBossAppKt.getMApplication());
        mXRecyclerView.setAdapter(mChatAdapter);
        event();
    }

    private void event() {
        mChatAdapter.setItemClickListener(new MessageChatAdapter.ItemClickListener() {
            @Override
            public void OnClick(int position) {
                ToastUtils.showShort("这是第"+position+"行");
            }

            @Override
            public void LongOnClick(int position) {

            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}