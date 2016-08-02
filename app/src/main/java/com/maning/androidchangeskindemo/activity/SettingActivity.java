package com.maning.androidchangeskindemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import com.kyleduo.switchbutton.ColorUtils;
import com.kyleduo.switchbutton.SwitchButton;
import com.maning.androidchangeskindemo.R;
import com.maning.androidchangeskindemo.skin.SkinManager;

public class SettingActivity extends BaseActivity {

    private static final String TAG = "SettingActivity";
    private SwitchButton btn_checked;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        context = this;

        btn_checked = (SwitchButton) findViewById(R.id.btn_checked);

        int currentSkinType = SkinManager.getCurrentSkinType(SettingActivity.this);
        if (SkinManager.THEME_DAY == currentSkinType) {
            btn_checked.setCheckedImmediately(false);
            btn_checked.setBackColorRes(R.color.kswBackColor);
            btn_checked.setThumbColorRes(R.color.kswThumbColor);
        } else {
            btn_checked.setCheckedImmediately(true);
            btn_checked.setBackColorRes(R.color.kswBackColor_night);
            btn_checked.setThumbColorRes(R.color.kswThumbColor_night);
        }

        btn_checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i(TAG, "isChecked:" + isChecked);
                int currentSkinType = SkinManager.getCurrentSkinType(SettingActivity.this);
                if (SkinManager.THEME_DAY == currentSkinType) {
                    SkinManager.changeSkin(SettingActivity.this, SkinManager.THEME_NIGHT);
                    btn_checked.setBackColorRes(R.color.kswBackColor_night);
                    btn_checked.setThumbColorRes(R.color.kswThumbColor_night);
                } else {
                    SkinManager.changeSkin(SettingActivity.this, SkinManager.THEME_DAY);
                    btn_checked.setBackColorRes(R.color.kswBackColor);
                    btn_checked.setThumbColorRes(R.color.kswThumbColor);
                }
                //重新启动
                change();
            }
        });

    }


    public void change(){
        Log.i(TAG, "change");
        startActivity(new Intent(context.getApplicationContext(),SettingActivity.class));
        this.finish();
        overridePendingTransition(R.anim.activity_enter,R.anim.activity_exit);
    }

}
