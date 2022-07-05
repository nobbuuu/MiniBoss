package com.dream.miniboss.app;

import android.app.Application;

/**
 * 创建日期：2022-07-05 on 10:56
 * 描述:衣带渐宽终不悔、为伊消得人憔悴
 * 作者:HeGuiCun Administrator
 */
public class AndroidApplication  extends Application {
    private static AndroidApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static AndroidApplication getInstance(){
        return instance;
    }

}
