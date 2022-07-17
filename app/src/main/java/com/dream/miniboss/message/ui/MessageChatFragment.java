package com.dream.miniboss.message.ui;

import static com.airbnb.lottie.L.TAG;

import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.dream.miniboss.MiniBossAppKt;
import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseFragment;
import com.dream.miniboss.message.adapter.MessageViewPagerAdapter;
import com.dream.miniboss.message.utils.IndexViewPager;
import com.google.android.material.tabs.TabLayout;
import com.hjq.bar.TitleBar;

import java.util.ArrayList;
import java.util.List;

public class MessageChatFragment  extends BaseFragment {
    private static final String TAG="TAG";
    TitleBar mTitleBar;
    TabLayout mTabLayout;
    IndexViewPager mViewPager;
    MessageViewPagerAdapter messageViewPagerAdapter;
    List<Fragment> mfragments;
    @Override
    protected int setLayout() {
        return R.layout.fragment_chat_message;
    }

    @Override
    protected void initView() {
        mTitleBar=fvbyid(R.id.title_bar_message);
        mTabLayout=fvbyid(R.id.tab_message_chat);
        mViewPager=fvbyid(R.id.message_view_pager);
    }

    @Override
    protected void initData() {
        event();
        mfragments=new ArrayList<>();
        mfragments.add(new MessagePersonFragment());
        mfragments.add(new InterestedMessageFragment());
        mfragments.add(new CollectionMessageFragment());
        messageViewPagerAdapter=new MessageViewPagerAdapter(getParentFragmentManager(),0,mfragments, MiniBossAppKt.getMApplication());
//        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mViewPager.setScanScroll(false);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_START);
        mViewPager.setAdapter(messageViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < messageViewPagerAdapter.getCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(messageViewPagerAdapter.getTabView(i));
            } else if (i==0){
                // 设置第一个tab的TextView是被选择的样式
                mTabLayout.getTabAt(0).getCustomView().setSelected(true); //第一个tab被选中

            }
        }

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getCustomView().setSelected(true);
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
              tab.getCustomView().setSelected(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //

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
