package com.dream.miniboss.message.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;

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
import com.sdk.base.api.CallBack;

import java.util.ArrayList;
import java.util.List;


public class MessagePersonFragment extends BaseFragment {
    XRecyclerView mXRecyclerView;
    MessageChatAdapter mChatAdapter;
    List<MessageChatBean> mChatBeanList;
    View mHeadView;
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
        mXRecyclerView.setPullRefreshEnabled(false);
        //item滑动删除
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(new MessageChatAdapter.CallBack());
        itemTouchHelper.attachToRecyclerView(mXRecyclerView);


        event();
        //给recyclerview增加头布局
        mHeadView = LayoutInflater.from(MiniBossAppKt.getMApplication()).inflate(R.layout.head_message_person,null);
        mXRecyclerView.addHeaderView(mHeadView);
        //给系统的头布局增加点击事件
        mHeadView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MiniBossAppKt.getMApplication(),SystemNotifyActivity.class));
                ToastUtils.showShort("这是头布局");
            }
        });

    }

    private void event() {
        mChatAdapter.setItemClickListener(new MessageChatAdapter.ItemClickListener() {
            @Override
            public void OnClick(int position) {
                ToastUtils.showShort("这是第"+position+"行");
                startActivity(new Intent(MiniBossAppKt.getMApplication(),MessageChatDetailActivity.class));
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