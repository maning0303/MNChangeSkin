package com.maning.androidchangeskindemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.maning.androidchangeskindemo.R;
import com.maning.androidchangeskindemo.activity.other.ColorMatrixActivity;
import com.maning.androidchangeskindemo.adapter.SkinAdapter;
import com.maning.themelibrary.SkinBroadcastReceiver;
import com.maning.themelibrary.SkinManager;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ListView listView;
    private SkinBroadcastReceiver skinBroadcastReceiver;
    private Button mBtnColormatrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        initView();
        initAdapter();
        //注册夜间模式广播监听
        registerSkinReceiver();
    }

    private void initAdapter() {
        SkinAdapter skinAdapter = new SkinAdapter(this);
        listView.setAdapter(skinAdapter);
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
        Button btn_setting = (Button) findViewById(R.id.btn_setting);
        Button btn_change = (Button) findViewById(R.id.btn_change);
        Button btn_webview = (Button) findViewById(R.id.btn_webview);
        btn_webview.setOnClickListener(this);
        btn_setting.setOnClickListener(this);
        btn_change.setOnClickListener(this);
        mBtnColormatrix = (Button) findViewById(R.id.btn_colormatrix);
        mBtnColormatrix.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_webview:
                startActivity(new Intent(MainActivity.this, WebViewActivity.class));
                break;
            case R.id.btn_setting:
                startActivity(new Intent(MainActivity.this, OtherActivity.class));
                break;
            case R.id.btn_change:
                SkinManager.changeSkin(this);
                resetActivity();
                break;
            case R.id.btn_colormatrix:
                startActivity(new Intent(this, ColorMatrixActivity.class));
                break;
        }
    }

    //重启当前Activity
    private void resetActivity() {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(com.maning.themelibrary.R.anim.mn_theme_activity_enter, com.maning.themelibrary.R.anim.mn_theme_activity_exit);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterSkinReceiver();
    }

    public void registerSkinReceiver() {
        skinBroadcastReceiver = new SkinBroadcastReceiver() {
            @Override
            public void onChangeSkin(int currentTheme) {
                Log.i("onChangeSkin", "MainActivity广播来了" + currentTheme);
                recreate();
            }
        };
        SkinManager.registerSkinReceiver(this, skinBroadcastReceiver);
    }

    public void unregisterSkinReceiver() {
        if (skinBroadcastReceiver != null) {
            SkinManager.unregisterSkinReceiver(this, skinBroadcastReceiver);
        }
    }

}
