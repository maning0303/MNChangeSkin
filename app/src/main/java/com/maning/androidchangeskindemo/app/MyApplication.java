package com.maning.androidchangeskindemo.app;

import android.app.Application;

import com.maning.androidchangeskindemo.R;
import com.maning.themelibrary.SkinManager;

/**
 * Created by maning on 2017/6/5.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initTheme();
    }

    private void initTheme() {
        SkinManager.setThemeID(R.style.DayTheme, R.style.NightTheme);
    }
}
