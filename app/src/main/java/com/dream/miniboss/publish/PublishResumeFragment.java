package com.dream.miniboss.publish;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;

import androidx.annotation.ColorInt;

import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseFragment;
import com.hjq.bar.TitleBar;
import com.ruffian.library.widget.RTextView;

/**
 * 创建日期：2022-07-04 on 0:12
 * 描述:衣带渐宽终不悔、为伊消得人憔悴
 * 作者:HeGuiCun Administrator
 */
public class PublishResumeFragment extends BaseFragment {
    private static final String TAG = "TAG";
    RTextView rtvUnskilled;
    TitleBar mTitleBar;
    @Override
    protected int setLayout() {
        return R.layout.fragment_publish;
    }

    @Override
    protected void initView() {
        mTitleBar=fvbyid(R.id.title_Publish);
        rtvUnskilled=fvbyid(R.id.rtvUnskilled);
    }

    @Override
    protected void initData() {

    mTitleBar.getLeftView().setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getActivity().onBackPressed();
            Log.i(TAG, "onClick: "+"--------333");
        }
    });
    event();

    }

    private void event() {
        rtvUnskilled.setOnClickListener(this);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.rtvUnskilled:

              break;
          default:

      }
    }
}
