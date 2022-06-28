package com.dream.miniboss.mine.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.animation.SlideInLeftAnimation;
import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;
import com.dream.miniboss.bean.BlackListBean;
import com.dream.miniboss.controller.BlackListAdapter;
import com.hjq.bar.TitleBar;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BlackListActivity extends BaseActivity {
    XRecyclerView mXRecyclerView;
    List<BlackListBean> mList;
    BlackListAdapter mBlackListAdapter;
    TitleBar mTitleBar;
    @Override
    protected int initLayout() {
        return R.layout.activity_black_list;
    }

    @Override
    protected void initView() {
        mXRecyclerView=fvbi(R.id.rv_black_list);
        mTitleBar=fvbi(R.id.title_black_list);
        mList = new ArrayList<>();
        mBlackListAdapter=new BlackListAdapter(mList,this);
        for (int i = 0; i < 10; i++) {

            mList.add(new BlackListBean(R.mipmap.blackicon, "饶女士", "个人招聘"));
        }
        mXRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mXRecyclerView.setAdapter(mBlackListAdapter);

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
    }
}