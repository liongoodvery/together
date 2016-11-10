package org.lion.alloytimer;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by lion on 11/10/16.
 */

public class App extends Application {
    public static Context sContext;
    public static Handler sMainThreadHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        sMainThreadHandler=new Handler(getMainLooper());
    }
}
