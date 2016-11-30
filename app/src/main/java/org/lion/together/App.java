package org.lion.together;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.logger.Logger;

import org.lion.together.di.componets.AppComponent;
import org.lion.together.di.componets.DaggerAppComponent;
import org.lion.together.di.modules.AppModule;

/**
 * Created by lion on 11/10/16.
 */

public class App extends Application {

    private static AppComponent sAppComponent;
    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        initLogger();
        Fresco.initialize(this);
        sAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        sContext = this;
    }

    private void initLogger() {
        Logger.init(C.LOG_TAG);                 // default PRETTYLOGGER or use just init()
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }
}
