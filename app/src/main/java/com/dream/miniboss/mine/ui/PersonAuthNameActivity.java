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
    public static final int TAKE_CAMERA = 101;
    public static final int PICK_PHOTO = 102;
    TitleBar mTitleBar;
    RImageView idCardOn, idCardOff;
    Uri imageUri;
    Bitmap bitmap = null;
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
                myDialog = new MyDialog(PersonAuthNameActivity.this, MyDialog.PICK_AVATAR);
                myDialog.show();
                getPicture();
                getCamera();
            }
        });
        //上传身份证反面
        idCardOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog = new MyDialog(PersonAuthNameActivity.this, MyDialog.PICK_AVATAR);
                myDialog.show();
                getPicture();
                getCamera();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_CAMERA:
                if (resultCode == RESULT_OK) {
                    try {
                        // 将拍摄的照片显示出来
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        idCardOn.setImageBitmap(bitmap);
                        idCardOff.setImageBitmap(bitmap);
                        //mBinding.iconUser.setImageResource(R.mipmap.usericon_grey);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case PICK_PHOTO:
                if (resultCode == RESULT_OK) {
                    // 判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        // 4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    } else {
                        // 4.4以下系统使用这个方法处理图片
                        handleImageBeforeKitKat(data);
                    }
                }

                break;
            default:
                break;
        }
    }

    /**
     * 获取相机资源
     */
    private void getCamera() {
        myDialog.mSelectCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: " + "点击拍照上传");

                // 创建File对象，用于存储拍照后的图片
                //存放在手机SD卡的应用关联缓存目录下
                File outputImage = new File(getExternalCacheDir(), "user_images.jpg");
                   /* 从Android 6.0系统开始，读写SD卡被列为了危险权限，如果将图片存放在SD卡的任何其他目录，
                      都要进行运行时权限处理才行，而使用应用关联 目录则可以跳过这一步
                    */
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                    /*
                       7.0系统开始，直接使用本地真实路径的Uri被认为是不安全的，会抛 出一个FileUriExposedException异常。
                       而FileProvider则是一种特殊的内容提供器，它使用了和内 容提供器类似的机制来对数据进行保护，
                       可以选择性地将封装过的Uri共享给外部，从而提高了 应用的安全性
                     */
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    //大于等于版本24（7.0）的场合
                    imageUri = FileProvider.getUriForFile(PersonAuthNameActivity.this, "com.dream.miniboss.FileProvider", outputImage);
                } else {
                    //小于android 版本7.0（24）的场合
                    imageUri = Uri.fromFile(outputImage);
                }

                //启动相机程序
                if (ContextCompat.checkSelfPermission(PersonAuthNameActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(PersonAuthNameActivity.this, new String[]{Manifest.permission
                            .WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 102);

                } else {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //MediaStore.ACTION_IMAGE_CAPTURE = android.media.action.IMAGE_CAPTURE
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//                        startActivityForResult(intent, TAKE_CAMERA);
                    startActivityIfNeeded(intent, TAKE_CAMERA);
                }
            }
        });
    }

    /**
     * 获取图片资源
     */
    private void getPicture() {
        myDialog.mSelectPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(PersonAuthNameActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(PersonAuthNameActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
                } else {
                    //打开相册
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    //Intent.ACTION_GET_CONTENT = "android.intent.action.GET_CONTENT"
                    intent.setType("image/*");
//                            startActivityForResult(intent, PICK_PHOTO); // 打开相册
                    startActivityIfNeeded(intent, PICK_PHOTO);
                }
            }

        });
    }


    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        // 根据图片路径显示图片
        displayImage(imagePath);
    }

    /**
     * android 4.4以前的处理方式
     *
     * @param data
     */
    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            idCardOn.setImageBitmap(bitmap);
            idCardOff.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "获取相册图片失败", Toast.LENGTH_SHORT).show();
        }
    }
}