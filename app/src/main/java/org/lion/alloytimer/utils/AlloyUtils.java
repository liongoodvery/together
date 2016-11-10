package org.lion.alloytimer.utils;

import org.lion.alloytimer.App;

/**
 * Created by lion on 11/10/16.
 */

public class AlloyUtils {
    public static void runOnUiThread(Runnable r) {
        App.sMainThreadHandler.post(r);
    }

    public static void runOnUiThread(Runnable r, long delayMillis) {
        App.sMainThreadHandler.postDelayed(r, delayMillis);
    }
}


