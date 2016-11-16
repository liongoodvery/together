package org.lion.alloytimer.di.modules;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;

import org.lion.alloytimer.config.C;
import org.lion.alloytimer.http.GistApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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

    @Singleton
    @Provides
    public GistApi provideGistApi(){
        return  new Retrofit.Builder()
                .baseUrl(C.BASE_GIST_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(GistApi.class);
    }
}
