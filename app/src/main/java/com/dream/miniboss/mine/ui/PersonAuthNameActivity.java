package com.dream.miniboss.mine.ui;

import android.graphics.Bitmap;
import android.view.View;
import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;

import com.hjq.bar.TitleBar;
import com.huawei.hms.mlplugin.card.icr.cn.MLCnIcrCapture;
import com.huawei.hms.mlplugin.card.icr.cn.MLCnIcrCaptureConfig;
import com.huawei.hms.mlplugin.card.icr.cn.MLCnIcrCaptureFactory;
import com.huawei.hms.mlplugin.card.icr.cn.MLCnIcrCaptureResult;
import com.ruffian.library.widget.RImageView;

public class PersonAuthNameActivity extends BaseActivity {
    private boolean lastType = false; // false: front， true：back.
    TitleBar mTitleBar;
    RImageView idCardOn, idCardOff;

    //MyDialog myDialog;

    @Override
    protected int initLayout() {
        return R.layout.activity_person_auth_name;
    }

    @Override
    protected void initView() {
        mTitleBar = fvbi(R.id.title_Authy_person);
        idCardOn = fvbi(R.id.idCard_on);
        idCardOff = fvbi(R.id.idCard_off);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void initData() {
        event();
    }

    /**
     * huawei身份证正反面识别
     */
    public MLCnIcrCapture.CallBack idCallback = new MLCnIcrCapture.CallBack() {
        @Override
        public void onSuccess(MLCnIcrCaptureResult idCardResult) {
            // 识别成功处理。
            Bitmap bitmap = idCardResult.cardBitmap;
            if (idCardResult == null) {
                return;
            }
            if (lastType) {
                idCardOn.setImageBitmap(bitmap);
            } else {
                idCardOff.setImageBitmap(bitmap);
            }
        }

        @Override
        public void onCanceled() {
            // 用户取消处理。
        }

        // 识别不到任何文字信息或识别过程发生系统异常的回调方法。
        // retCode：错误码。
        // bitmap：检测失败的身份证图片。
        @Override
        public void onFailure(int retCode, Bitmap bitmap) {
            // 识别异常处理。
        }

        @Override
        public void onDenied() {
            // 相机不支持等场景处理。
        }
    };

    private void startCaptureActivity(MLCnIcrCapture.CallBack callback, boolean isFront, boolean isRemote) {
        MLCnIcrCaptureConfig config = new MLCnIcrCaptureConfig.Factory()
                // 设置识别身份证的正反面。
                // true：正面。
                // false：反面。
                .setFront(lastType)
                // 设置是否使用云侧能力进行识别。
                // true：云侧。
                // false：端侧。
                .setRemote(false)
                .create();
        MLCnIcrCapture icrCapture = MLCnIcrCaptureFactory.getInstance().getIcrCapture(config);
        icrCapture.capture(callback, this);
    }

    private void event() {
        mTitleBar.getLeftView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //上传身份证正面
        idCardOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastType = true;
                startCaptureActivity(idCallback, lastType, false);
            }
        });
        //上传身份证反面
        idCardOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastType = false;
                startCaptureActivity(idCallback, lastType, false);
            }
        });

    }


}
