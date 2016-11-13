package org.lion.alloytimer;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import org.lion.alloytimer.di.componets.AppComponent;
import org.lion.alloytimer.di.componets.DaggerAppComponent;
import org.lion.alloytimer.di.modules.AppModule;

import dagger.internal.DaggerCollections;

/**
 * Created by lion on 11/10/16.
 */

public class App extends Application {

    private static AppComponent mAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }
}
