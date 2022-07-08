package com.dream.miniboss.mine.ui;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.blankj.utilcode.util.ToastUtils;
import com.dream.miniboss.R;
import com.dream.miniboss.base.BaseActivity;
import com.dream.miniboss.databinding.ActivityUserEditBinding;
import com.dream.miniboss.main.MainActivity;
import com.dream.miniboss.utils.MyDialog;
import com.dream.miniboss.utils.UploadUtil;
import com.example.liangmutian.mypicker.DataPickerDialog;
import com.example.liangmutian.mypicker.DatePickerDialog;
import com.example.liangmutian.mypicker.DateUtil;
import com.ruffian.library.widget.RImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 创建日期：2022-06-24 on 1:05
 * 描述:衣带渐宽终不悔、为伊消得人憔悴
 * 作者:HeGuiCun Administrator
 */
public class UserEditActivity extends BaseActivity implements View.OnClickListener {

    public static final int TAKE_CAMERA = 101;
    public static final int PICK_PHOTO = 102;
    private Uri imageUri;
    private Bitmap bitmap = null;
    private Dialog dateDialog, chooseDialog;
    private List<String> list = new ArrayList<>();
    private ActivityUserEditBinding mBinding;
    private SharedPreferences mPreferences;

    @Override
    protected int initLayout() {
        return R.layout.activity_user_edit;
    }

    @Override
    protected void initView() {
        mBinding = ActivityUserEditBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        //自定义数据里面循环加入数据
        String[] data = getResources().getStringArray(R.array.list);
        for (String str : data) {
            list.add(str);
        }
        //取出存储数据显示在界面上
        mPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        mBinding.etName.setText(mPreferences.getString("et_name", ""));
        mBinding.etGender.setText(mPreferences.getString("et_gender", ""));
        mBinding.etBirthTime.setText(mPreferences.getString("et_birth_time", ""));
        mBinding.etEmil.setText(mPreferences.getString("et_emil", ""));
        //将存储的图片显示在头像上面

        try {
            File outputImage = new File(getExternalCacheDir(), "user_images.jpg");
            imageUri = FileProvider.getUriForFile(UserEditActivity.this, "com.dream.miniboss.FileProvider", outputImage);
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (bitmap != null) {
            mBinding.iconUser.setImageBitmap(bitmap);
        } else {
            mBinding.iconUser.setImageResource(R.mipmap.usericon_grey);
        }
    }

    @Override
    protected void initData() {
        onEvent();
    }

    private void onEvent() {
        mBinding.iconUser.setOnClickListener(this);
        mBinding.etName.setOnClickListener(this);
        mBinding.etGender.setOnClickListener(this);
        mBinding.etBirthTime.setOnClickListener(this);
        mBinding.etEmil.setOnClickListener(this);
        mBinding.buttonPull.setOnClickListener(this);
        //返回按钮的监听事件
        mBinding.titleBar.getLeftView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mBinding.scviewIv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (mBinding.etName.isFocusable()) {
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    mBinding.etName.setFocusable(false);
                    mBinding.etName.setFocusableInTouchMode(false);
                } else if (mBinding.etEmil.isFocusable()) {
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    mBinding.etEmil.setFocusable(false);
                    mBinding.etEmil.setFocusableInTouchMode(false);
                }
                return false;
            }
        });
    }

    /**
     * 设置性别选择
     * chooseDialog
     */
    private void showChooseDialog(List<String> mlist) {
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(1).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
//                        mTextView.setText(itemValue);
                        //设置数据源
                        mBinding.etGender.setText(itemValue);

                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

    /**
     * 设置出生日期
     *
     * @param date
     */
    private void showDateDialog(List<Integer> date) {
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(this);
        builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
                    @Override
                    public void onDateSelected(int[] dates) {

                        mBinding.etBirthTime.setText(dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                                + (dates[2] > 9 ? dates[2] : ("0" + dates[2])));

                    }

                    @Override
                    public void onCancel() {
                        ToastUtils.showShort("请选择你的出生年月");
                    }
                })

                .setSelectYear(date.get(0) - 1)
                .setSelectMonth(date.get(1) - 1)
                .setSelectDay(date.get(2) - 1);

        builder.setMaxYear(DateUtil.getYear());
        builder.setMaxMonth(DateUtil.getDateForString(DateUtil.getToday()).get(1));
        builder.setMaxDay(DateUtil.getDateForString(DateUtil.getToday()).get(2));
        dateDialog = builder.create();
        dateDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_user:
                //这里上传头
                MyDialog myDialog = new MyDialog(this, MyDialog.PICK_AVATAR);
                myDialog.show();
                myDialog.mSelectPicture.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ContextCompat.checkSelfPermission(UserEditActivity.this,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(UserEditActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 102);
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
//                myDialog.dismiss();

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
                            imageUri = FileProvider.getUriForFile(UserEditActivity.this, "com.dream.miniboss.FileProvider", outputImage);
                            Log.i(TAG, "--------onClick: " + imageUri);
                        } else {
                            //小于android 版本7.0（24）的场合
                            imageUri = Uri.fromFile(outputImage);
                        }

                        //启动相机程序
                        if (ContextCompat.checkSelfPermission(UserEditActivity.this,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(UserEditActivity.this, new String[]{Manifest.permission
                                    .WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 102);
//                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                            //MediaStore.ACTION_IMAGE_CAPTURE = android.media.action.IMAGE_CAPTURE
//                            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
////                        startActivityForResult(intent, TAKE_CAMERA);
//                            startActivityIfNeeded(intent, TAKE_CAMERA);
                        } else {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            //MediaStore.ACTION_IMAGE_CAPTURE = android.media.action.IMAGE_CAPTURE
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//                        startActivityForResult(intent, TAKE_CAMERA);
                            startActivityIfNeeded(intent, TAKE_CAMERA);
                        }
                    }
                });
//                myDialog.dismiss();
                break;
            case R.id.et_name:
//                Log.i(TAG, "onViewClicked: 这是名字"+mEtName);
                mBinding.etName.setFocusable(true);
                mBinding.etName.setFocusableInTouchMode(true);

                mBinding.etName.requestFocus();
                mBinding.etName.setSelection(mBinding.etName.getText().length());

                break;
            case R.id.et_gender:
                showChooseDialog(list);

                break;
            case R.id.et_birth_time:
                showDateDialog(DateUtil.getDateForString("1990-01-01"));

                break;
            case R.id.et_emil:
                mBinding.etEmil.setFocusable(true);
                mBinding.etEmil.setFocusableInTouchMode(true);
                mBinding.etEmil.requestFocus();
                //临时存储数据
                mBinding.etEmil.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                mBinding.etEmil.setSelection(mBinding.etEmil.getText().length());
                break;
            case R.id.buttonPull:
                //临时存储,名字数据

                SharedPreferences.Editor etNameEditor = mPreferences.edit();
                etNameEditor.putString("et_name", mBinding.etName.getText().toString());
                etNameEditor.commit();
                //临时存储,性别数据
                SharedPreferences.Editor genderEditor = mPreferences.edit();
                genderEditor.putString("et_gender", mBinding.etGender.getText().toString());
                genderEditor.commit();
                //临时存储出生年月数据
                SharedPreferences.Editor birthEditor = mPreferences.edit();
                birthEditor.putString("et_birth_time", mBinding.etBirthTime.getText().toString());
                birthEditor.commit();
                //临时存储描述数据
                SharedPreferences.Editor descEditor = mPreferences.edit();
                descEditor.putString("et_desc", "完善信息找工作更快哦");
                descEditor.commit();

                //验证邮箱格式
                if (isEmailValid(mBinding.etEmil.getText().toString())) {
                    //临时存储邮箱数据
                    SharedPreferences.Editor emailEditor = mPreferences.edit();
                    emailEditor.putString("et_emil", mBinding.etEmil.getText().toString());
                    emailEditor.commit();
//                    Intent mIntent=new Intent();
//                    mIntent.setClass(UserEditActivity.this, MainActivity.class);
//                    mIntent.putExtra("id",0);
//                    startActivity(mIntent);
                    onBackPressed();
                } else {
                    ToastUtils.showShort("请输入正确的邮箱格式");
                }


                break;

            default:

        }
    }

    /**
     * 验证邮箱格式
     *
     * @param email
     * @return
     */
    public boolean isEmailValid(String email) {

        String regExpn =

                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"

                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"

                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."

                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"

                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"

                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())

            return true;

        else

            return false;

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
                        mBinding.iconUser.setImageBitmap(bitmap);
                        //mBinding.iconUser.setImageResource(R.mipmap.usericon_grey);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case PICK_PHOTO:
                if (resultCode == RESULT_OK) { // 判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 16) {
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

    @TargetApi(19)
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
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content: //downloads/public_downloads"), Long.valueOf(docId));
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
            mBinding.iconUser.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "获取图片失败", Toast.LENGTH_SHORT).show();
        }
    }

}
