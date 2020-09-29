package com.maning.androidchangeskindemo.activity.other;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.maning.androidchangeskindemo.R;

/**
 * 引用：https://github.com/lenebf/AppDress
 */
public class ColorMatrixActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 护眼模式
     */
    private Button mBtnEye;
    /**
     * 黑白模式
     */
    private Button mBtnBlackWhite;
    /**
     * 正常模式
     */
    private Button mBtnNormal;
    /**
     * 夜间模式
     */
    private Button mBtnNight;
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colormatrix);
        initView();
    }

    private void initView() {
        mBtnEye = (Button) findViewById(R.id.btn_eye);
        mBtnEye.setOnClickListener(this);
        mBtnBlackWhite = (Button) findViewById(R.id.btn_black_white);
        mBtnBlackWhite.setOnClickListener(this);
        mBtnNormal = (Button) findViewById(R.id.btn_normal);
        mBtnNormal.setOnClickListener(this);
        mBtnNight = (Button) findViewById(R.id.btn_night);
        mBtnNight.setOnClickListener(this);
        mSeekBar = (SeekBar) findViewById(R.id.seek_bar);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float progressF = (float) progress / 100;
                ColorMatrixUtils.initEyeshield(ColorMatrixActivity.this, progressF);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_eye:
                mSeekBar.setProgress(70);
                ColorMatrixUtils.initEyeshield(this);
                break;
            case R.id.btn_black_white:
                mSeekBar.setProgress(100);
                ColorMatrixUtils.initBlackWhite(this);
                break;
            case R.id.btn_normal:
                mSeekBar.setProgress(100);
                ColorMatrixUtils.initNormal(this);
                break;
            case R.id.btn_night:
                mSeekBar.setProgress(100);
                ColorMatrixUtils.initNight(this);
                break;
        }
    }
}
