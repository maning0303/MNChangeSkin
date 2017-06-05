package com.maning.themelibrary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by maning on 16/3/29.
 * 接收主题改变的广播
 */
public abstract class SkinBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int currentTheme = intent.getIntExtra(SkinManager.IntentExtra_SkinTheme, 0);
        onChangeSkin(currentTheme);
    }

    public void onChangeSkin(int currentTheme) {

    }

}
