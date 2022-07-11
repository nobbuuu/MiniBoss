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
    LinearLayout monPersonLayout,partTimeCertificateLayout;
    FrameLayout mTeamCountLayout,mDayMoneyLayout,mMonthMoneyLayout,mDayNumLayout;
    RadioGroup styleResumeGroup, teamStyleGroup,moneyStyleGroup;
    //这是兼职注册师
    View oneView,twoView;
    LinearLayout gradCertificateLayout,professionLayout;
    RadioGroup postNameRadioGroup,certificateRadioGroup,gradRadioGroup,safeRadioGroup,socialRadioGroup;

    @Override
    protected int initLayout() {
        return R.layout.activity_publish_resume;
    }

    @Override
    protected void initView() {
        mTitleBar = fvbi(R.id.title_bar);
        monPersonLayout=fvbi(R.id.mon_person_lv);
        partTimeCertificateLayout=fvbi(R.id.part_time_certified_ll);

        mTeamCountLayout=fvbi(R.id.team_count_fragment);
        mDayMoneyLayout=fvbi(R.id.money_day_fragment);
        mMonthMoneyLayout=fvbi(R.id.money_month_fragment);
        mDayNumLayout=fvbi(R.id.money_number_fragment);
        styleResumeGroup = fvbi(R.id.style_resume_group);
        teamStyleGroup = fvbi(R.id.team_mine_group);
        moneyStyleGroup=fvbi(R.id.style_money_group);
        //这是兼职注册师的初始化group
        oneView=fvbi(R.id.view_one);
        twoView=fvbi(R.id.view_two);

        postNameRadioGroup=fvbi(R.id.post_name_radioGroup);
        certificateRadioGroup=fvbi(R.id.professional_certificate_radioGroup);
        gradRadioGroup=fvbi(R.id.grade_certificate_radioGroup);
        safeRadioGroup=fvbi(R.id.safe_certification_radioGroup);
        socialRadioGroup=fvbi(R.id.social_security_radioGroup);
        //
        gradCertificateLayout=fvbi(R.id.grade_certificate_ll);
        professionLayout=fvbi(R.id.profier_certificate_ll);

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
                    partTimeCertificateLayout.setVisibility(View.GONE);
                }else {
                    ToastUtils.showShort(jianRadioButton.getText().toString());
                    monPersonLayout.setVisibility(View.GONE);
                    partTimeCertificateLayout.setVisibility(View.VISIBLE);
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
        //兼职注册师的radioGroup所有的监听事件
        postNameRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i <2 ; i++) {
                    RadioButton postNameRadiobutton= (RadioButton) group.getChildAt(i);
                    if (postNameRadiobutton.isChecked()){
                        ToastUtils.showShort(postNameRadiobutton.getText().toString());
                    }
                }
            }
        });
        certificateRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton noCertificateRadioButton= (RadioButton) group.getChildAt(0);
                RadioButton CertificateRadioButton= (RadioButton) group.getChildAt(1);
                if (noCertificateRadioButton.isChecked()){
                    gradCertificateLayout.setVisibility(View.GONE);
                    professionLayout.setVisibility(View.GONE);
                    oneView.setVisibility(View.GONE);
                    twoView.setVisibility(View.GONE);
                    ToastUtils.showShort(noCertificateRadioButton.getText().toString());
                }else if (CertificateRadioButton.isChecked()){
                    gradCertificateLayout.setVisibility(View.VISIBLE);
                    professionLayout.setVisibility(View.VISIBLE);
                    oneView.setVisibility(View.VISIBLE);
                    twoView.setVisibility(View.VISIBLE);
                    ToastUtils.showShort(CertificateRadioButton.getText().toString());
                }
            }
        });


    }
}