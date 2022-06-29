package com.dream.miniboss.mine.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;
import com.hjq.bar.TitleBar;
import com.ruffian.library.widget.RTextView;

public class FeedbackActivity extends BaseActivity {
    TitleBar mTitleBar;
    RTextView mTextViewSoftNoUse;
    boolean flag=false;
    @Override
    protected int initLayout() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initView() {
        mTitleBar = fvbi(R.id.title_feed_back);
        mTextViewSoftNoUse=fvbi(R.id.rtv_soft_notUse);
    }

    @Override
    protected void initData() {
        event();
    }

    private void event() {
        //点击标题栏的返回按钮
        mTitleBar.getLeftView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        //点击RTextview
     mTextViewSoftNoUse.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            if (flag==false){
                Log.i(TAG, "onClick: "+mTextViewSoftNoUse.getText());
               //mTextViewSoftNoUse.getHelper().getIconPressedRight().setVisible(false,false);
                mTextViewSoftNoUse.getHelper().getIconPressedRight().setVisible(true,false);
                flag=true;
            }else {
                Log.i(TAG, "onClick: "+mTextViewSoftNoUse.getHelper().getIconPressedRight());
                mTextViewSoftNoUse.getHelper().getIconPressedRight().setVisible(false,false);
                flag=false;
            }
         }
     });

    }
}