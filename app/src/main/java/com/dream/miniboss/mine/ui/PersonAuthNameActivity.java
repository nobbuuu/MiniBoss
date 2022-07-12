package com.dream.miniboss.mine.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;
import com.dream.miniboss.utils.MyDialog;
import com.hjq.bar.TitleBar;
import com.ruffian.library.widget.RImageView;

import java.io.File;
import java.io.FileNotFoundException;

public class PersonAuthNameActivity extends BaseActivity {

    TitleBar mTitleBar;
    RImageView idCardOn, idCardOff;

    MyDialog myDialog;

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
    protected void initData() {
        myDialog = new MyDialog(PersonAuthNameActivity.this, MyDialog.PICK_AVATAR);
        event();
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
                myDialog.show();

            }
        });
        //上传身份证反面
        idCardOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.show();

            }
        });

    }



}