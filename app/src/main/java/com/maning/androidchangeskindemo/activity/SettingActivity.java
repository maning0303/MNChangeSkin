package com.maning.androidchangeskindemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;

import com.kyleduo.switchbutton.SwitchButton;
import com.maning.androidchangeskindemo.R;
import com.maning.themelibrary.SkinManager;

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
                SkinManager.changeSkin(SettingActivity.this);
                resetActivity();
            }
        });

    }

    //重启当前Activity
    private void resetActivity() {
        startActivity(new Intent(this, SettingActivity.class));
        overridePendingTransition(com.maning.themelibrary.R.anim.mn_theme_activity_enter, com.maning.themelibrary.R.anim.mn_theme_activity_exit);
        finish();
    }

}
