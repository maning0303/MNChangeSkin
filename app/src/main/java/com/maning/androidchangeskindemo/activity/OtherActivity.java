package com.maning.androidchangeskindemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.maning.androidchangeskindemo.R;
import com.maning.themelibrary.SkinBroadcastReceiver;
import com.maning.themelibrary.SkinManager;

public class OtherActivity extends BaseActivity {


    private SkinBroadcastReceiver skinBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        findViewById(R.id.rl_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OtherActivity.this, SettingActivity.class));
            }
        });

        registerSkinReceiver();

    }

    public void registerSkinReceiver() {
        skinBroadcastReceiver = new SkinBroadcastReceiver() {
            @Override
            public void onChangeSkin(int currentTheme) {
                Log.i("onChangeSkin", "OtherActivity广播来了" + currentTheme);
                recreate();
            }
        };
        SkinManager.registerSkinReceiver(OtherActivity.this, skinBroadcastReceiver);
    }

    public void unregisterSkinReceiver() {
        if (skinBroadcastReceiver != null) {
            SkinManager.unregisterSkinReceiver(this, skinBroadcastReceiver);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterSkinReceiver();
    }


}
