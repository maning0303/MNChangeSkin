package com.maning.androidchangeskindemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.maning.androidchangeskindemo.R;
import com.maning.androidchangeskindemo.adapter.SkinAdapter;
import com.maning.androidchangeskindemo.skin.SkinBroadcastReceiver;
import com.maning.androidchangeskindemo.skin.SkinManager;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ListView listView;
    private SkinBroadcastReceiver skinBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        initView();
        initAdapter();
    }

    private void registerSkinReceiver() {
        if (skinBroadcastReceiver == null) {
            skinBroadcastReceiver = new SkinBroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Log.i("onReceive", "MainActivity广播来了");
                    recreate();
                }
            };
            SkinManager.registerSkinReceiver(MainActivity.this, skinBroadcastReceiver);
        }
    }

    private void initAdapter() {
        SkinAdapter skinAdapter = new SkinAdapter(this);
        listView.setAdapter(skinAdapter);
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
        Button btn_setting = (Button) findViewById(R.id.btn_setting);
        Button btn_change = (Button) findViewById(R.id.btn_change);
        btn_setting.setOnClickListener(this);
        btn_change.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_setting:
                registerSkinReceiver();
                startActivity(new Intent(MainActivity.this, OtherActivity.class));
                break;
            case R.id.btn_change:
                int currentSkinType = SkinManager.getCurrentSkinType(MainActivity.this);
                if (SkinManager.THEME_DAY == currentSkinType) {
                    SkinManager.changeSkin(MainActivity.this, SkinManager.THEME_NIGHT);
                } else {
                    SkinManager.changeSkin(MainActivity.this, SkinManager.THEME_DAY);
                }
                startActivity(new Intent(this.getApplicationContext(),MainActivity.class));
                this.finish();
                overridePendingTransition(R.anim.activity_enter,R.anim.activity_exit);
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.unregisterSkinReceiver(this, skinBroadcastReceiver);
    }
}
