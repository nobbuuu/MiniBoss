package com.dream.miniboss.message.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;
import com.dream.miniboss.message.adapter.MessageChatDetailAdapter;
import com.dream.miniboss.message.bean.MessageChatDetailBean;
import com.hjq.bar.TitleBar;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ruffian.library.widget.REditText;

import java.util.ArrayList;
import java.util.List;

public class MessageChatDetailActivity extends BaseActivity {
     TitleBar chatMessageTitle;
     XRecyclerView mRecyclerView;
    List<MessageChatDetailBean> messageList;
    MessageChatDetailAdapter mAdapter;
    REditText mChatMessageEt;
    Button sendBtn,receiveBtn;
    @Override
    protected int initLayout() {
        return R.layout.activity_message_chat_detail;
    }

    @Override
    protected void initView() {
        chatMessageTitle=fvbi(R.id.chat_message_title);
        mRecyclerView=fvbi(R.id.chat_message_rv);
        mChatMessageEt=fvbi(R.id.chat_message_input_et);
        //
        sendBtn=fvbi(R.id.btn_send);
        receiveBtn=fvbi(R.id.btn_receive);
    }

    @Override
    protected void initData() {
        //
        mRecyclerView.setPullRefreshEnabled(false);
        //
        messageList=new ArrayList<>();
        mAdapter=new MessageChatDetailAdapter(messageList,this);
        mRecyclerView.setAdapter(mAdapter);
       event();
       //增加数据
        MessageChatDetailBean leftMessage = new MessageChatDetailBean("你好呀！", MessageChatDetailBean.TYPE_RECEIVED);
        MessageChatDetailBean leftMessage1 = new MessageChatDetailBean("嗯嗯", MessageChatDetailBean.TYPE_SEND);
        MessageChatDetailBean leftMessage2 = new MessageChatDetailBean("哈哈哈哈", MessageChatDetailBean.TYPE_RECEIVED);
        messageList.add(leftMessage);
        messageList.add(leftMessage1);
        messageList.add(leftMessage2);

    }

    private void event() {
        chatMessageTitle.getLeftView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //
        sendBtn.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View view) {
                String editText = mChatMessageEt.getText().toString();
                if (!editText.equals("")) {
                    MessageChatDetailBean receivedMessage = new MessageChatDetailBean(editText, MessageChatDetailBean.TYPE_SEND);
                    messageList.add(receivedMessage);
                    // 把数据添加到最后一行
                    mAdapter.notifyItemInserted(messageList.size() - 1);
                    // 滑动列表定位到最后一行
                    mRecyclerView.scrollToPosition(messageList.size() - 1);
                    mChatMessageEt.setText("");
                }
            }
        });
        //
        receiveBtn.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View view) {
                String editText = mChatMessageEt.getText().toString();
                if (!editText.equals("")) {
                    MessageChatDetailBean receivedMessage = new MessageChatDetailBean(editText, MessageChatDetailBean.TYPE_RECEIVED);
                    messageList.add(receivedMessage);
                    // 把数据添加到最后一行
                    mAdapter.notifyItemInserted(messageList.size() - 1);
                    // 滑动列表定位到最后一行
                    mRecyclerView.scrollToPosition(messageList.size() - 1);
                    mChatMessageEt.setText("");
                }

            }
        });
    }
}