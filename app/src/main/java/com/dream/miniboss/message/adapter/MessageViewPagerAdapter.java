package com.dream.miniboss.message.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.dream.miniboss.MiniBossAppKt;
import com.dream.miniboss.R;
import com.ruffian.library.widget.RImageView;
import com.ruffian.library.widget.RTextView;

import java.util.List;

public class MessageViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;
    Context mContext;
    private String tabTitles[] = new String[]{"消息","赞过我","收藏过我"};
    public MessageViewPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Fragment> fragments, Context mContext) {
        super(fm, behavior);
        this.fragments = fragments;
        this.mContext = mContext;
    }


//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return tabTitles[position];
//    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


    @Override
    public int getCount() {
        return fragments.size();
    }
    public View getTabView(int position) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_viewpager_message, null);
        RTextView tv = (RTextView) v.findViewById(R.id.message_tv);
//        RImageView imageView=v.findViewById(R.id.red_message_iv);
//        imageView.setImageResource(R.mipmap.redmessage);
        tv.setText(tabTitles[position]);
        return v;
    }



}
