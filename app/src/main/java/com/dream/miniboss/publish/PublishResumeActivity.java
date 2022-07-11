package com.dream.miniboss.publish;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;
import com.hjq.bar.TitleBar;

public class PublishResumeActivity extends BaseActivity {
    TitleBar mTitleBar;
    LinearLayout monPersonLayout;
    FrameLayout mTeamCountLayout,mDayMoneyLayout,mMonthMoneyLayout,mDayNumLayout;
    RadioGroup styleResumeGroup, teamStyleGroup,moneyStyleGroup;

    @Override
    protected int initLayout() {
        return R.layout.activity_publish_resume;
    }

    @Override
    protected void initView() {
        mTitleBar = fvbi(R.id.title_bar);
        monPersonLayout=fvbi(R.id.mon_person_lv);

        mTeamCountLayout=fvbi(R.id.team_count_fragment);
        mDayMoneyLayout=fvbi(R.id.money_day_fragment);
        mMonthMoneyLayout=fvbi(R.id.money_month_fragment);
        mDayNumLayout=fvbi(R.id.money_number_fragment);
        styleResumeGroup = fvbi(R.id.style_resume_group);
        teamStyleGroup = fvbi(R.id.team_mine_group);
        moneyStyleGroup=fvbi(R.id.style_money_group);

    }

    @Override
    protected void initData() {
        event();
    }

    private void event() {
        //返回按钮的事件
        mTitleBar.getLeftView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //求职类型的事件
        styleResumeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton comRadioButton = (RadioButton) styleResumeGroup.getChildAt(0);
                RadioButton jianRadioButton = (RadioButton) styleResumeGroup.getChildAt(0);

                if (comRadioButton.isChecked()) {
                        ToastUtils.showShort(comRadioButton.getText().toString());
                         monPersonLayout.setVisibility(View.VISIBLE);
                }else {
                    ToastUtils.showShort(jianRadioButton.getText().toString());
                    monPersonLayout.setVisibility(View.GONE);
                }
            }
        });
        //是否自带班队
        teamStyleGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton mRadioButton = (RadioButton) teamStyleGroup.getChildAt(0);
                RadioButton mXRadioButton = (RadioButton) teamStyleGroup.getChildAt(1);
                if (mRadioButton.isChecked()) {
                    mTeamCountLayout.setVisibility(View.GONE);
                    ToastUtils.showShort(mRadioButton.getText().toString());
                } else {
                    mTeamCountLayout.setVisibility(View.VISIBLE);
                    ToastUtils.showShort(mXRadioButton.getText().toString());

                }


            }

        });
        moneyStyleGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton mDayRadiobutton = (RadioButton) moneyStyleGroup.getChildAt(0);
                RadioButton mMonthRadiobutton = (RadioButton) moneyStyleGroup.getChildAt(1);
                RadioButton mNumRadiobutton = (RadioButton) moneyStyleGroup.getChildAt(2);
                RadioButton mChatRadiobutton = (RadioButton) moneyStyleGroup.getChildAt(3);
                if (mDayRadiobutton.isChecked()) {
                    ToastUtils.showShort(mDayRadiobutton.getText().toString());
                    mDayMoneyLayout.setVisibility(View.VISIBLE);
                    mMonthMoneyLayout.setVisibility(View.GONE);
                    mDayNumLayout.setVisibility(View.GONE);

                } else if (mMonthRadiobutton.isChecked()){
                    ToastUtils.showShort(mMonthRadiobutton.getText().toString());
                    mDayMoneyLayout.setVisibility(View.GONE);
                    mMonthMoneyLayout.setVisibility(View.VISIBLE);
                    mDayNumLayout.setVisibility(View.GONE);

                }else if (mNumRadiobutton.isChecked()){
                    ToastUtils.showShort(mNumRadiobutton.getText().toString());

                    mDayMoneyLayout.setVisibility(View.GONE);
                    mMonthMoneyLayout.setVisibility(View.GONE);
                    mDayNumLayout.setVisibility(View.VISIBLE);
                }else if (mChatRadiobutton.isChecked()){
                    ToastUtils.showShort(mChatRadiobutton.getText().toString());
                    mDayMoneyLayout.setVisibility(View.GONE);
                    mMonthMoneyLayout.setVisibility(View.GONE);
                    mDayNumLayout.setVisibility(View.GONE);
                }


            }
        });
    }
}