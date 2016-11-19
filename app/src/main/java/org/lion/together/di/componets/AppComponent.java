package org.lion.together.di.componets;

import android.content.Context;
import android.os.Handler;
import android.os.Vibrator;

import org.lion.together.di.modules.AppModule;
import org.lion.together.http.GistApi;

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
    GistApi getGistApi();
}
