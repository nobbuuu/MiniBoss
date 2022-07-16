package com.dream.miniboss.message.ui;

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
        mfragments=new ArrayList<>();
        mfragments.add(new MessagePersonFragment());
        mfragments.add(new InterestedMessageFragment());
        mfragments.add(new CollectionMessageFragment());
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(messageViewPagerAdapter.getTabView(i));
            }
        }
        messageViewPagerAdapter=new MessageViewPagerAdapter(getParentFragmentManager(),0,mfragments, MiniBossAppKt.getMApplication());
//      viewPager.setCurrentItem(1);
//        mTabLayout.getTabAt(0).getCustomView().setSelected(true);

        mViewPager.setAdapter(messageViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
          event();
       mViewPager.setScanScroll(false);

//    mViewPager.setAdapter();

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
