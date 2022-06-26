package com.dream.miniboss.controller;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.List;

/**
 * 创建日期：2022-06-21 on 2:47
 * 描述:衣带渐宽终不悔、为伊消得人憔悴
 * 作者:HeGuiCun Administrator
 */
public class TabAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;
    private FragmentManager mFragmentManager;

    public TabAdapter(FragmentManager mFragmentManager,List<Fragment> fragments) {
        super(mFragmentManager);
        this.mFragmentManager=mFragmentManager;
        this.fragments=fragments;

    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
