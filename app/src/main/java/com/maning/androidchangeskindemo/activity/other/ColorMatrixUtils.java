package com.maning.androidchangeskindemo.activity.other;

import android.app.Activity;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;

/**
 * @author : maning
 * @date : 2020-09-29
 * @desc :
 */
public class ColorMatrixUtils {

    public static void initNormal(Activity activity) {
        Window window = activity.getWindow();
        if (window == null) {
            return;
        }
        View view = window.getDecorView();
        view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
    }

    public static void initEyeshield(Activity activity) {
        initEyeshield(activity, 0.7f);
    }

    /**
     * @param activity
     * @param defaultEyeSrc 0f-1.0f 之间
     */
    public static void initEyeshield(Activity activity, float defaultEyeSrc) {
        Window window = activity.getWindow();
        if (window == null) {
            return;
        }
        View view = window.getDecorView();
        Paint paint = new Paint();
        // 我们把蓝色减弱为原来的0.7
        ColorMatrix cm = new ColorMatrix(new float[]{
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, defaultEyeSrc, 0, 0,
                0, 0, 0, 1, 0
        });
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        view.setLayerType(View.LAYER_TYPE_HARDWARE, paint);
    }

    public static void initBlackWhite(Activity activity) {
        Window window = activity.getWindow();
        if (window == null) {
            return;
        }
        View view = window.getDecorView();
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        // 关键起作用的代码，Saturation，翻译成中文就是饱和度的意思。
        // 官方文档说明：A value of 0 maps the color to gray-scale. 1 is identity.
        // 原来如此，666
        cm.setSaturation(0f);
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        view.setLayerType(View.LAYER_TYPE_HARDWARE, paint);
    }


    public static void initNight(Activity activity) {
        Window window = activity.getWindow();
        if (window == null) {
            return;
        }
        View view = window.getDecorView();
        Paint rootPaint = new Paint();
        ColorMatrix cm = new ColorMatrix(new float[]{
                -1, 0, 0, 0, 255,
                0, -1, 0, 0, 255,
                0, 0, -1, 0, 255,
                0, 0, 0, 1, 0
        });
        rootPaint.setColorFilter(new ColorMatrixColorFilter(cm));
        view.setLayerType(View.LAYER_TYPE_HARDWARE, rootPaint);
        //遍历子view
        if (view instanceof ViewGroup) {
            takeOffColor((ViewGroup) view);
        }

    }

    public static void takeOffColor(ViewGroup viewGroup) {
        // 遍历查找ImageView，对其设置逆矩阵
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View childView = viewGroup.getChildAt(i);
            if (childView instanceof ViewGroup) {
                takeOffColor((ViewGroup) childView);
            } else if (childView instanceof ImageView) {
                revert(childView);
            }
        }
    }

    public static void revert(@NonNull View view) {
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix(new float[]{
                -1, 0, 0, 0, 255,
                0, -1, 0, 0, 255,
                0, 0, -1, 0, 255,
                0, 0, 0, 1, 0
        });
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        view.setLayerType(View.LAYER_TYPE_HARDWARE, paint);
    }

}
