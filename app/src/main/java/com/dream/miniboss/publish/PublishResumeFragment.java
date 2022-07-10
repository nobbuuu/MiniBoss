package com.dream.miniboss.publish;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.ColorInt;

import com.blankj.utilcode.util.ToastUtils;
import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseFragment;
import com.hjq.bar.TitleBar;
import com.ruffian.library.widget.RRadioButton;
import com.ruffian.library.widget.RTextView;

/**
 * 创建日期：2022-07-04 on 0:12
 * 描述:衣带渐宽终不悔、为伊消得人憔悴
 * 作者:HeGuiCun Administrator
 */
public class PublishResumeFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "TAG";
    RadioGroup skillSelectGroup,workTimeSelectGroup;
    RTextView publishResume;
    TitleBar mTitleBar;

    @Override
    protected int setLayout() {
        return R.layout.fragment_publish;
    }

    @Override
    protected void initView() {
        mTitleBar = fvbyid(R.id.title_Publish);
        skillSelectGroup = fvbyid(R.id.radio_group_skill);
        publishResume = fvbyid(R.id.textView_publish);
    }

    @Override
    protected void initData() {

        mTitleBar.getLeftView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        event();

    }

    private void event() {
        skillSelectGroup.setOnCheckedChangeListener(this);
        workTimeSelectGroup.setOnCheckedChangeListener(this);
        publishResume.setOnClickListener(this);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.textView_publish:
                startActivity(new Intent(getContext(), PublishResumeActivity.class));
                break;
            default:

        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getId()){

        }
        for (int i = 0; i < 4; i++) {
            RadioButton mRadioButton = (RadioButton) skillSelectGroup.getChildAt(i);
            if (mRadioButton.isChecked()) {
                ToastUtils.showShort(mRadioButton.getText().toString());
                break;

            }
        }
    }
}
