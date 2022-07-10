package com.dream.miniboss.publish;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;
import com.hjq.bar.TitleBar;

public class PublishResumeActivity extends BaseActivity {
    TitleBar mTitleBar;
    RadioGroup styleResumeGroup,teamMyGroup,moneyStyleGroup;
    @Override
    protected int initLayout() {
        return R.layout.activity_publish_resume;
    }

    @Override
    protected void initView() {
        mTitleBar=fvbi(R.id.title_bar);
        styleResumeGroup=fvbi(R.id.style_resume_group);
        teamMyGroup=fvbi(R.id.team_mine_group);
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
                for (int i = 0; i < 2; i++) {
                    RadioButton mRadioButton = (RadioButton) styleResumeGroup.getChildAt(i);
                    if (mRadioButton.isChecked()) {
                        ToastUtils.showShort(mRadioButton.getText().toString());
                    }
                }
            }
        });
        //是否自带班队事件监听
        teamMyGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < 2; i++) {
                    RadioButton mRadioButton = (RadioButton) teamMyGroup.getChildAt(i);
                    if (mRadioButton.isChecked()) {
                        ToastUtils.showShort(mRadioButton.getText().toString());
                    }
                }
            }
        });
        //结算方式事件监听
        moneyStyleGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < 4; i++) {
                    RadioButton mRadioButton = (RadioButton) moneyStyleGroup.getChildAt(i);
                    if (mRadioButton.isChecked()) {
                        ToastUtils.showShort(mRadioButton.getText().toString());
                    }
                }
            }
        });
    }
}