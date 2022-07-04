package com.dream.miniboss.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

import androidx.core.content.ContextCompat;

import com.dream.miniboss.R;

/**
 * 创建日期：2022-07-05 on 1:01
 * 描述:衣带渐宽终不悔、为伊消得人憔悴
 * 作者:HeGuiCun Administrator
 */
public class LoginUIHelper {

    public LoginUIHelper() {
    }

    public static float pxToDp(float px) {

        float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;

        return px / (densityDpi / 160f);

    }



    public static int dpToPx(float dp) {

        float density = Resources.getSystem().getDisplayMetrics().density;

        return Math.round(dp * density);

    }



    public static void changeIconDrawableToGray(Context context, Drawable drawable) {

        if (drawable != null) {

            drawable.mutate();

            drawable.setColorFilter(ContextCompat.getColor(context, R.color.gray_bg), PorterDuff.Mode.SRC_ATOP);

        }

    }

}
