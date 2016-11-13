package org.lion.alloytimer.di.modules;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lion on 11/11/16.
 */
@Module
public class AppModule {
    Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return mContext;
    }

    @Singleton
    @Provides
    public Handler provideHandler() {
        return new Handler(Looper.getMainLooper());
    }


    @Singleton
    @Provides
    public Vibrator provideVibrator() {
        return (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
    }
}
