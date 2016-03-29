package com.maning.androidchangeskindemo.activity;

import android.app.Activity;
import android.os.Bundle;

import com.maning.androidchangeskindemo.skin.SkinManager;

/**
 * Created by maning on 16/3/28.
 */
public class BaseActivity extends Activity {

    //当前Activity的主题
    public int skinBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //设置主题
        SkinManager.onActivityCreateSetSkin(this);
        super.onCreate(savedInstanceState);
        //获取当前的主题
        skinBase = SkinManager.getCurrentSkinType(this);
    }

}
