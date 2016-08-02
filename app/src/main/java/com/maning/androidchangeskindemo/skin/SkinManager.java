package com.maning.androidchangeskindemo.skin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;

import com.maning.androidchangeskindemo.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by maning on 16/3/28.
 * <p/>
 * 切换主题的管理工具
 */
public class SkinManager {
    public final static int THEME_DAY = 0;
    public final static int THEME_NIGHT = 1;
    private static String CONFIG = "config";
    private static String SkinKey = "SkinKey";
    private static SharedPreferences sp;

    public static final String IntentExtra_SkinTheme = "IntentExtra_SkinTheme";
    private static final String IntentActionSkin = "com.change_skin.action";
    private static final IntentFilter intentFilter = new IntentFilter(IntentActionSkin);
    private static final Intent intent = new Intent(IntentActionSkin);

    /**
     * 注册广播，主要防止设置界面太深，而之前的页面改不了，更换主题必须重启Activity才能有效果
     * @param activity
     * @param skinBroadcastReceiver
     */
    public static void registerSkinReceiver(Activity activity, SkinBroadcastReceiver skinBroadcastReceiver) {
        if (skinBroadcastReceiver != null) {
            activity.registerReceiver(skinBroadcastReceiver, intentFilter);
        }
    }

    public static void unregisterSkinReceiver(Activity activity, SkinBroadcastReceiver skinBroadcastReceiver) {
        if (skinBroadcastReceiver != null) {
            activity.unregisterReceiver(skinBroadcastReceiver);
        }
    }

    /**
     * 获取当前主题的Type
     * @param context
     * @return  0：白天主题；1：夜间主题
     */
    public static int getCurrentSkinType(Context context) {
        return getSharePreSkin(context, THEME_DAY);
    }

    private static void setSkinType(Context context, int theme) {
        saveSharePreSkin(context, theme);
    }

    /**
     * 获取当前主题
     * @param context
     * @return
     */
    public static int getCurrentSkinTheme(Context context) {
        int saveSkinType = getCurrentSkinType(context);
        int currentTheme;
        switch (saveSkinType) {
            default:
            case THEME_DAY:
                currentTheme = R.style.DayTheme;
                break;
            case THEME_NIGHT:
                currentTheme = R.style.NightTheme;
                break;
        }
        return currentTheme;
    }

    /**
     * 改变主题皮肤
     *
     * @param activity 当前Activity
     * @param theme    两种选择
     */
    public static void changeSkin(Activity activity, int theme) {
        int currentTheme;
        switch (theme) {
            default:
            case THEME_DAY:
                setSkinType(activity.getApplicationContext(), THEME_DAY);
                currentTheme = R.style.DayTheme;
                break;
            case THEME_NIGHT:
                setSkinType(activity.getApplicationContext(), THEME_NIGHT);
                currentTheme = R.style.NightTheme;
                break;
        }
        //发送广播
        intent.putExtra(IntentExtra_SkinTheme, currentTheme);
        activity.sendBroadcast(intent);
    }


    public static void onActivityCreateSetSkin(Activity activity) {
        int currentSkinTheme = getCurrentSkinTheme(activity.getApplicationContext());
        activity.setTheme(currentSkinTheme);
    }

    private static void saveSharePreSkin(Context context, int value) {
        if (sp == null) {
            sp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        sp.edit().putInt(SkinKey, value).apply();
    }

    private static int getSharePreSkin(Context context, int defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        return sp.getInt(SkinKey, defValue);
    }

}
