package org.lion.alloytimer.di.componets;

import android.content.Context;
import android.os.Handler;
import android.os.Vibrator;

import org.lion.alloytimer.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lion on 11/11/16.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Context getContext();
    Handler getHandler();
    Vibrator getVibrator();
}
