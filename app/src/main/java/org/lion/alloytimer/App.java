package org.lion.alloytimer;

import android.app.Application;
import android.content.Context;

import org.lion.alloytimer.di.componets.AppComponent;
import org.lion.alloytimer.di.componets.DaggerAppComponent;
import org.lion.alloytimer.di.modules.AppModule;

/**
 * Created by lion on 11/10/16.
 */

public class App extends Application {

    private static AppComponent mAppComponent;
    public static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        sContext = this;
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }
}
