package org.lion.alloytimer.utils;

import android.text.TextUtils;

import com.facebook.drawee.view.SimpleDraweeView;

import org.lion.alloytimer.App;

/**
 * Created by lion on 11/10/16.
 */

public class AlloyUtils {
    public static void runOnUiThread(Runnable r) {
        App.getAppComponent().getHandler().post(r);
    }

    public static void runOnUiThread(Runnable r, long delayMillis) {
        App.getAppComponent().getHandler().postDelayed(r, delayMillis);
    }

    public static void StartVibrate(long[] pattern, int repeat) {
        App.getAppComponent().getVibrator().vibrate(pattern, repeat);
    }
    public static void StartVibrate(long milis) {
        App.getAppComponent().getVibrator().vibrate(milis);
    }
    public static void stopVibrate(long[] pattern, int repeat) {
        App.getAppComponent().getVibrator().cancel();
    }
    public static void setImageUrl(SimpleDraweeView draweeView , String url){
        if (TextUtils.isEmpty(url)) {
            return;
        }
        draweeView.setImageURI(url);
    }
}


