package com.maning.androidchangeskindemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.maning.androidchangeskindemo.R;
import com.maning.androidchangeskindemo.skin.SkinBroadcastReceiver;
import com.maning.androidchangeskindemo.skin.SkinManager;

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

        skinBroadcastReceiver = new SkinBroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int currentTheme = intent.getIntExtra(SkinManager.IntentExtra_SkinTheme, 0);
                Log.i("onReceive", "OtherActivity广播来了" + currentTheme);
                recreate();
            }
        };
        SkinManager.registerSkinReceiver(OtherActivity.this, skinBroadcastReceiver);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.unregisterSkinReceiver(this, skinBroadcastReceiver);
    }
}
